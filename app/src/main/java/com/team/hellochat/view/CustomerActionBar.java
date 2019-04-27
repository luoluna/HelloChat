package com.team.hellochat.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.hellochat.R;
import com.team.hellochat.interf.OnClickActionBar;

import java.net.URL;

/**
 * Created by Sweven on 2019/3/15.
 * Email:sweventears@Foxmail.com
 */
public class CustomerActionBar extends LinearLayout {

    private static final int LEFT_BTN = 0x1;
    private static final int RIGHT_BTN = 0x2;
    private static final int TEXT_BTN = 0x3;
    private static final int IMAGE_BTN = 0x4;


    private RelativeLayout layout;
    private TextView tvTitle;
    private RelativeLayout rlLeft;
    private RelativeLayout rlRight;
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivLeft;
    private ImageView ivRight;

    private int which;
    private int type;
    private OnClickActionBar.OnClickLeftListener onClickLeftListener;
    private OnClickActionBar.OnClickRightListener onClickRightListener;
    private String title;
    private String sLeft;
    private String sRight;
    private Object oLeft;
    private Object oRight;

    private Context context;

    public CustomerActionBar(Context context) {
        super(context);
    }

    public CustomerActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    private void onCreate(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.customer_action_bar, this);

        layout = findViewById(R.id.action_bar_layout);
        tvTitle = findViewById(R.id.bar_title);
        rlLeft = findViewById(R.id.rl_left);
        rlRight = findViewById(R.id.rl_right);
        tvLeft = findViewById(R.id.tv_left);
        tvRight = findViewById(R.id.tv_right);
        ivLeft = findViewById(R.id.iv_left);
        ivRight = findViewById(R.id.iv_right);

        initData();
    }

    private void initData() {
        rlLeft.setVisibility(INVISIBLE);
        rlRight.setVisibility(INVISIBLE);
        tvLeft.setVisibility(INVISIBLE);
        tvRight.setVisibility(INVISIBLE);
        ivLeft.setVisibility(INVISIBLE);
        ivRight.setVisibility(INVISIBLE);

        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));

        tvLeft.setOnClickListener(this::click);
        tvRight.setOnClickListener(this::click);
        ivLeft.setOnClickListener(this::click);
        ivRight.setOnClickListener(this::click);

    }

    public void setThemeColor(int color) {
        layout.setBackgroundColor(color);
    }

    public void setThemeResource(int resource) {
        layout.setBackgroundResource(resource);
    }

    private void click(View v) {
        int i = v.getId();
        if (i == R.id.tv_left || i == R.id.iv_left) {
            if (onClickLeftListener != null) {
                onClickLeftListener.onClick();
            }
        } else if (i == R.id.tv_right || i == R.id.iv_right) {
            if (onClickRightListener != null) {
                onClickRightListener.onClick();
            }
        }
    }

    public CustomerActionBar setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomerActionBar setTitle(int resId) {
        this.title = getResources().getString(resId);
        return this;
    }

    public Which showLeft() {
        return new Which(this, LEFT_BTN);
    }

    public Which showRight() {
        return new Which(this, RIGHT_BTN);
    }

    private void build() {
        tvTitle.setText(title == null || title.equals("") ? getResources().getString(R.string.app_name) : title);
        if (which == LEFT_BTN) {
            rlLeft.setVisibility(VISIBLE);
            if (type == TEXT_BTN) {
                tvLeft.setVisibility(VISIBLE);
                tvLeft.setText(sLeft);
            } else if (type == IMAGE_BTN) {
                ivLeft.setVisibility(VISIBLE);
                setImageObject(oLeft);
            }
        } else if (which == RIGHT_BTN) {
            rlRight.setVisibility(VISIBLE);
            if (type == TEXT_BTN) {
                tvRight.setVisibility(VISIBLE);
                tvRight.setText(sRight);
            } else if (type == IMAGE_BTN) {
                ivRight.setVisibility(VISIBLE);
                setImageObject(oRight);
            }
        }
    }

    public void onlyTitle(String title) {
        this.title = title;
        build();
    }

    public void onlyTitle(int resId) {
        this.title = getResources().getString(resId);
        build();
    }

    public void showImageBack(String title, Activity activity) {
        this.title = title;
        showLeft()
                .setImageResource(R.drawable.ic_left_btn).
                setOnClickLeftListener(new OnClickActionBar.OnClickLeftListener() {
                    @Override
                    public void onClick() {
                        activity.finish();
                    }
                })
                .build();
    }

    public void showImageBack(int resId, Activity activity) {
        this.title = getResources().getString(resId);
        showLeft()
                .setImageResource(R.drawable.ic_left_btn).
                setOnClickLeftListener(new OnClickActionBar.OnClickLeftListener() {
                    @Override
                    public void onClick() {
                        activity.finish();
                    }
                })
                .build();
    }

    public class Which {

        private int which;
        private int type;
        private CustomerActionBar customerActionBar;
        private OnClickActionBar.OnClickLeftListener onClickLeftListener;
        private OnClickActionBar.OnClickRightListener onClickRightListener;
        private String sLeft;
        private String sRight;
        private Object oLeft;
        private Object oRight;

        public Which(CustomerActionBar customerActionBar, int which) {
            this.customerActionBar = customerActionBar;
            this.which = which;
            this.onClickLeftListener = customerActionBar.onClickLeftListener;
            this.onClickRightListener = customerActionBar.onClickRightListener;
            this.sLeft = customerActionBar.sLeft;
            this.sRight = customerActionBar.sRight;
            this.type = customerActionBar.type;
        }

        public Listener setText(int resId) {
            if (which == LEFT_BTN) {
                sLeft = getResources().getString(resId);
            } else if (which == RIGHT_BTN) {
                sRight = getResources().getString(resId);
            }
            type = TEXT_BTN;
            return new Listener(this);
        }

        public Listener setText(String text) {
            if (which == LEFT_BTN) {
                sLeft = text;
            } else if (which == RIGHT_BTN) {
                sRight = text;
            }
            type = TEXT_BTN;
            return new Listener(this);
        }

        public Listener setImageResource(int resId) {
            if (which == LEFT_BTN) {
                oLeft = resId;
            } else if (which == RIGHT_BTN) {
                oRight = resId;
            }
            type = IMAGE_BTN;
            return new Listener(this);
        }

        public Listener setImageBitmap(Bitmap bm) {
            if (which == LEFT_BTN) {
                oLeft = bm;
            } else if (which == RIGHT_BTN) {
                oRight = bm;
            }
            type = IMAGE_BTN;
            return new Listener(this);
        }

        public Listener setImageDrawable(Drawable drawable) {
            if (which == LEFT_BTN) {
                oLeft = drawable;
            } else if (which == RIGHT_BTN) {
                oRight = drawable;
            }
            type = IMAGE_BTN;
            return new Listener(this);
        }

        public Listener setImageURI(Uri uri) {
            if (which == LEFT_BTN) {
                oLeft = uri;
            } else if (which == RIGHT_BTN) {
                oRight = uri;
            }
            type = IMAGE_BTN;
            return new Listener(this);
        }


        public class Listener {

            private Which which;
            private OnClickActionBar.OnClickLeftListener onClickLeftListener;
            private OnClickActionBar.OnClickRightListener onClickRightListener;

            public Listener(Which which) {
                this.which = which;
                onClickLeftListener = which.onClickLeftListener;
                onClickRightListener = which.onClickRightListener;
            }

            public Listener setOnClickLeftListener(OnClickActionBar.OnClickLeftListener onClickLeftListener) {
                this.onClickLeftListener = onClickLeftListener;
                return this;
            }

            public Listener setOnClickRightListener(OnClickActionBar.OnClickRightListener onClickRightListener) {
                this.onClickRightListener = onClickRightListener;
                return this;
            }

            public void build() {
                which.onClickLeftListener = this.onClickLeftListener;
                which.onClickRightListener = this.onClickRightListener;
                which.build();
            }

            public Which showLeft() {
                which.onClickLeftListener = this.onClickLeftListener;
                which.onClickRightListener = this.onClickRightListener;
                return which.showLeft();
            }

            public Which showRight() {
                which.onClickLeftListener = this.onClickLeftListener;
                which.onClickRightListener = this.onClickRightListener;
                return which.showRight();
            }

        }

        private Which showLeft() {
            customerActionBar.which = this.which;
            customerActionBar.type = this.type;
            customerActionBar.onClickLeftListener = this.onClickLeftListener;
            customerActionBar.onClickRightListener = this.onClickRightListener;
            customerActionBar.sLeft = this.sLeft;
            customerActionBar.sRight = this.sRight;
            customerActionBar.oLeft = this.oLeft;
            customerActionBar.oRight = this.oRight;
            customerActionBar.build();
            this.which = LEFT_BTN;
            return this;
        }

        private Which showRight() {
            customerActionBar.which = this.which;
            customerActionBar.type = this.type;
            customerActionBar.onClickLeftListener = this.onClickLeftListener;
            customerActionBar.onClickRightListener = this.onClickRightListener;
            customerActionBar.sLeft = this.sLeft;
            customerActionBar.sRight = this.sRight;
            customerActionBar.oLeft = this.oLeft;
            customerActionBar.oRight = this.oRight;
            customerActionBar.build();
            this.which = RIGHT_BTN;
            return this;
        }

        private void build() {
            customerActionBar.which = this.which;
            customerActionBar.type = this.type;
            customerActionBar.onClickLeftListener = this.onClickLeftListener;
            customerActionBar.onClickRightListener = this.onClickRightListener;
            customerActionBar.sLeft = this.sLeft;
            customerActionBar.sRight = this.sRight;
            customerActionBar.oLeft = this.oLeft;
            customerActionBar.oRight = this.oRight;
            customerActionBar.build();
        }

    }

    private void setImageObject(Object oLeft) {
        if (oLeft instanceof Integer) {
            setImage((Integer) oLeft);
        } else if (oLeft instanceof Bitmap) {
            setImage((Bitmap) oLeft);
        } else if (oLeft instanceof Drawable) {
            setImage((Drawable) oLeft);
        } else if (oLeft instanceof URL) {
            setImage((URL) oLeft);
        } else if (oLeft instanceof Uri) {
            setImage((Uri) oLeft);
        }
    }

    public void setImage(int resId) {
        if (which == LEFT_BTN) {
            ivLeft.setImageResource(resId);
        } else if (which == RIGHT_BTN) {
            ivRight.setImageResource(resId);
        }
    }

    public void setImage(Bitmap bm) {
        if (which == LEFT_BTN) {
            ivLeft.setImageBitmap(bm);
        } else if (which == RIGHT_BTN) {
            ivRight.setImageBitmap(bm);
        }
    }

    public void setImage(Drawable drawable) {
        if (which == LEFT_BTN) {
            ivLeft.setImageDrawable(drawable);
        } else if (which == RIGHT_BTN) {
            ivRight.setImageDrawable(drawable);
        }
    }

    public void setImage(URL url) {
        if (which == LEFT_BTN) {
            Glide.with(context)
                    .load(url)
                    .into(ivLeft);
        } else if (which == RIGHT_BTN) {
            Glide.with(context)
                    .load(url)
                    .into(ivRight);
        }
    }

    public void setImage(Uri uri) {
        if (which == LEFT_BTN) {
            Glide.with(context)
                    .load(uri)
                    .into(ivLeft);
        } else if (which == RIGHT_BTN) {
            Glide.with(context)
                    .load(uri)
                    .into(ivRight);
        }
    }

}
