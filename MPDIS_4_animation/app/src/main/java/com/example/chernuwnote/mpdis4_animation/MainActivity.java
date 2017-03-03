package com.example.chernuwnote.mpdis4_animation;

import android.os.Bundle;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;



public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    SeekBar seekbar;
    Switch mSwitch;
    Boolean synchronize=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekbar  = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(this);


        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setChecked(false);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    synchronize = true;
                } else {
                    synchronize= false;
                }
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void startAnimation(View view) throws InterruptedException {
        float dest;
        ImageView aniView = (ImageView) findViewById(R.id.imageView1);
        ImageView aniView2 = (ImageView) findViewById(R.id.imageView2);
        switch (view.getId()) {

            case R.id.Button01:
                dest = 360;
                if (aniView.getRotation() == 360 & aniView2.getRotation() == 360) {
                    System.out.println(aniView.getAlpha());
                    System.out.println(aniView2.getAlpha());
                    dest = 0;
                }
                ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView, "rotation", dest);
                ObjectAnimator animation12 = ObjectAnimator.ofFloat(aniView2, "rotation", dest);

                if(synchronize){
                    animation1.setDuration((long)5000/(seekbar.getProgress()+1));
                    animation12.setDuration((long)5000/(seekbar.getProgress()+1));
                    animation1.start();
                    animation12.start();
                } else{
                    animation1.setDuration((long)5000/(seekbar.getProgress()+1));
                    animation12.setDuration((long)10000/(seekbar.getProgress()+1));
                    animation1.start();
                    animation12.start();
                }
                // Show how to load an animation from XML
                // Animation animation1 = AnimationUtils.loadAnimation(this,
                // R.anim.myanimation);
                // animation1.setAnimationListener(this);
                // animatedView1.startAnimation(animation1);
                break;

            case R.id.Button02:
                // Shows how to define a animation via code
                // Also use an Interpolator (BounceInterpolator)
                Paint paint = new Paint();
                TextView aniTextView = (TextView) findViewById(R.id.textView1);
                float measureTextCenter = paint.measureText(aniTextView.getText().toString());
                dest = 0 - measureTextCenter;
                if (aniTextView.getX() < 0) {
                    dest = 0;
                }
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(aniTextView,"x", dest);
                animation2.setDuration(2000);
                animation2.start();
                break;

            case R.id.Button03:
                // Demonstrate fading and adding an AnimationListener
                RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.layout);
                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.main_layout_animation);
                mainContainer.setLayoutAnimation(controller);

                dest = 1;
                Button button3=(Button)findViewById(R.id.Button03);
                button3.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.hyperspace_jump));
                if (aniView.getAlpha() > 0) {
                    dest = 0;
                }
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(aniView, "alpha", dest);
                animation3.setDuration(2000);
                animation3.start();
                break;

            case R.id.Button04:

                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",0f);
                fadeOut.setDuration(2000);
                ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,"translationX", -500f, 0f);
                mover.setDuration(2000);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",0f, 1f);
                fadeIn.setDuration(2000);
                AnimatorSet animatorSet = new AnimatorSet();

                animatorSet.play(mover).with(fadeIn).after(fadeOut);
                animatorSet.start();
                break;

            default:
                break;
        }
    }
}