package com.example.umnozhka;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MakeFotoActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String LOG_TAG = "myLogs";


    CameraService[] myCameras = null;

    private CameraManager mCameraManager = null;
    private final int CAMERA1 = 0;
    //        private final int CAMERA2   = 1;
    private Button mButtonOpenCamera1 = null;
    private Button mButtonToMakeShot = null;
    private TextureView mImageView = null;
    private ImageView mImageViewNew = null;

    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler = null;

    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundThread();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_foto);

        Log.d(LOG_TAG, "Запрашиваем разрешение");
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ||
                (ContextCompat.checkSelfPermission(MakeFotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


        mButtonOpenCamera1 = findViewById(R.id.MakeFotoActivityButton1);
        mButtonToMakeShot = findViewById(R.id.MakeFotoActivityButton2);
        mImageView = findViewById(R.id.textureView);

        mButtonOpenCamera1.setOnClickListener(this);
        mButtonToMakeShot.setOnClickListener(this);


        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            // Получение списка камер с устройства
            myCameras = new CameraService[mCameraManager.getCameraIdList().length];
            for (String cameraID : mCameraManager.getCameraIdList()) {
                Log.i(LOG_TAG, "cameraID: " + cameraID);
                int id = Integer.parseInt(cameraID);
                // создаем обработчик для камеры
                myCameras[id] = new CameraService(mCameraManager, cameraID);
            }
        } catch (CameraAccessException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MakeFotoActivityButton1:
                if (myCameras[CAMERA1] != null)
                    if (!myCameras[CAMERA1].isOpen())
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            myCameras[CAMERA1].openCamera();
                        }
                break;
            case R.id.MakeFotoActivityButton2:
//                myCameras[CAMERA1].takePicture(null, null, null, mPictureCallback);
//                myCameras[CAMERA1].openCamera();
                if (myCameras[CAMERA1].isOpen()) myCameras[CAMERA1].makePhoto();
                //FinishLeassonActivity.finishImageView =  myCameras[CAMERA1].mImageReader;


                break;
        }
    }

    private static class ImageSaver implements Runnable {

        private final File mFile;
        private Image mImageImageSaver;

        ImageSaver(Image image, File file) {
            mImageImageSaver = image;
            mFile = file;
        }

        @Override
        public void run() {
            ByteBuffer buffer = mImageImageSaver.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream(mFile);
                output.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mImageImageSaver.close();
                if (null != output) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class CameraService {
        private ImageReader mImageReader;
        private String mCameraID;
        private CameraDevice mCameraDevice = null;
        private CameraCaptureSession mCaptureSession;
        public CameraDevice.StateCallback mCameraCallback;
        CaptureRequest.Builder builder;
        private File mFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "test1.jpg");
        ;

        public CameraService(CameraManager cameraManager, String cameraID) {
            mCameraCallback = new CameraDevice.StateCallback() {

                @Override
                public void onOpened(CameraDevice camera) {
                    mCameraDevice = camera;
                    Log.i(LOG_TAG, "Open camera  with id:" + mCameraDevice.getId());

                    createCameraPreviewSession();
                }

                @Override
                public void onDisconnected(CameraDevice camera) {
                    mCameraDevice.close();

                    Log.i(LOG_TAG, "disconnect camera  with id:" + mCameraDevice.getId());
                    mCameraDevice = null;
                }

                @Override
                public void onError(CameraDevice camera, int error) {
                    Log.i(LOG_TAG, "error! camera id:" + camera.getId() + " error:" + error);
                }
            };

            mCameraManager = cameraManager;
            mCameraID = cameraID;

        }

        public void makePhoto() {
            try {

                // This is the CaptureRequest.Builder that we use to take a picture.
                final CaptureRequest.Builder captureBuilder =
                        mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                captureBuilder.addTarget(mImageReader.getSurface());
                CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {

                    @Override
                    public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                                   @NonNull CaptureRequest request,
                                                   @NonNull TotalCaptureResult result) {
//                        mBackgroundHandler.post(new ImageSaver(reader.acquireNextImage(), mFile));
//
//                        Image image = reader.acquireLatestImage();
//                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
//                        byte[] bytes = new byte[buffer.capacity()];
//                        buffer.get(bytes);
//                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
////                    mImageViewNew .setImageBitmap(bitmap);
//                        FinishLeassonActivity.finishImageView.setImageBitmap(bitmap);
//
//                        Toast.makeText(MakeFotoActivity.this, "фотка доступна для сохранения", Toast.LENGTH_SHORT).show();
                    }
                };

                mCaptureSession.stopRepeating();
                mCaptureSession.abortCaptures();
                //mCaptureSession.capture(captureBuilder.build(), captureCallback, mBackgroundHandler);
//                captureBuilder.

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        private final ImageReader.OnImageAvailableListener mOnImageAvailableListener
                = new ImageReader.OnImageAvailableListener() {

            @Override
            public void onImageAvailable(ImageReader reader) {
                    mBackgroundHandler.post(new ImageSaver(reader.acquireNextImage(), mFile));

                    Image image = reader.acquireLatestImage();
                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                    byte[] bytes = new byte[buffer.capacity()];
                    buffer.get(bytes);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                    mImageViewNew .setImageBitmap(bitmap);
                    FinishLeassonActivity.finishImageView.setImageBitmap(bitmap);

                    Toast.makeText(MakeFotoActivity.this, "фотка доступна для сохранения", Toast.LENGTH_SHORT).show();

            }
        };

        private void createCameraPreviewSession() {
            // Отрабатывается при нажатии кнопки Open Camera

            mImageReader = ImageReader.newInstance(1000, 800, ImageFormat.JPEG, 1);
            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, null);

            SurfaceTexture texture = mImageView.getSurfaceTexture();
            texture.setDefaultBufferSize(1000, 800);
            Surface surface = new Surface(texture);
            try {
                builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                builder.addTarget(surface);
                mCameraDevice.createCaptureSession(Arrays.asList(surface),stateCallback
                        , null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }


        CameraCaptureSession.StateCallback stateCallback =  new CameraCaptureSession.StateCallback()

        {
            @Override
            public void onConfigured (CameraCaptureSession session){
            mCaptureSession = session;
            try {
                mCaptureSession.setRepeatingRequest(builder.build(), null, mBackgroundHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

            @Override
            public void onConfigureFailed (CameraCaptureSession session){
        }
        };

        public boolean isOpen() {
            if (mCameraDevice == null) {
                return false;
            } else {
                return true;
            }
        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        public void openCamera() {
            try {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    mCameraManager.openCamera(mCameraID, mCameraCallback, mBackgroundHandler);
                }
            } catch (CameraAccessException e) {
                Log.i(LOG_TAG, e.getMessage());
            }
        }

        public void closeCamera() {
            if (mCameraDevice != null) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
        }

    }


    @Override
    public void onPause() {
        if (myCameras[CAMERA1].isOpen()) {
            myCameras[CAMERA1].closeCamera();
        }
        super.onPause();
        stopBackgroundThread();
    }


}


