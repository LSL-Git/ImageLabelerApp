package app.com.lsl.imagelabelerapp.lsl.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import app.com.lsl.imagelabelerapp.R;
import app.com.lsl.imagelabelerapp.lsl.activity.ShowPicInfoActivity;

/** 图片流布局适配器
 * Created by M1308_000 on 2017/5/17.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.BaseViewHolder> {

    private ArrayList<String> dataList = new ArrayList<>();
    private Resources res;

    public void replaceAll(ArrayList<String> list) {
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public PicAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_img_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PicAdapter.BaseViewHolder holder, int position) {
        //Log.e("ImageAdapter","pos " + position);
        holder.setData(dataList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        void setData(Object data, int pos) {
        }
    }

    private class OneViewHolder extends BaseViewHolder {
        private ImageView ivImage;
        private String img_url = "";
        private int pos;
        public OneViewHolder(View view) {
            super(view);
            ivImage = (ImageView) view.findViewById(R.id.iv_show_image_list);

            int width = 1024;
            //((Activity) ivImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();  // 获取屏幕宽度
//            Log.e("ImageAdapter","" + width);

            final ViewGroup.LayoutParams params = ivImage.getLayoutParams();
            //设置图片的相对于屏幕的宽高比
            params.width = width/3;
            params.height =  (int) (200 + Math.random() * 70) ;
            ivImage.setLayoutParams(params);
            res = itemView.getContext().getResources();

            /** 设置图片点击监听，跳转至图片标签页，并将图片的URL传过去 */
            ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), ShowPicInfoActivity.class);
                    intent.putExtra("img_url",img_url);
                    itemView.getContext().startActivity(intent);

                }
            });
        }

        @Override
        void setData(Object data, int pos) {
            this.pos = pos;
            if (data != null) {
                img_url = (String) data;
               //Log.e("ImageAdapter","setData text " + img_url + " pos " + pos);
                Glide.with(itemView.getContext()).load(img_url).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.mipmap.appicon).crossFade().into(ivImage);

                Bitmap bitmap = BitmapFactory.decodeResource(res,R.mipmap.icon);
                //异步获得bitmap图片颜色值
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力
                        Palette.Swatch c = palette.getDarkVibrantSwatch();//有活力 暗色
                        Palette.Swatch d = palette.getLightVibrantSwatch();//有活力 亮色
                        Palette.Swatch f = palette.getMutedSwatch();//柔和
                        Palette.Swatch a = palette.getDarkMutedSwatch();//柔和 暗色
                        Palette.Swatch b = palette.getLightMutedSwatch();//柔和 亮色

                        if (vibrant != null) {
                            int color1 = vibrant.getBodyTextColor();//内容颜色
                            int color2 = vibrant.getTitleTextColor();//标题颜色
                            int color3 = vibrant.getRgb();//rgb颜色

                            ivImage.setBackgroundColor(vibrant.getRgb());

                        }
                    }
                });
            }


        }
    }
}
