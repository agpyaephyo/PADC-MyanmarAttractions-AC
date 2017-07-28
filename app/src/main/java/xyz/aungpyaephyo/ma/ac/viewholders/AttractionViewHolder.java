package xyz.aungpyaephyo.ma.ac.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import xyz.aungpyaephyo.ma.ac.R;
import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.utils.AppConstants;

/**
 * Created by aung on 7/6/16.
 */
public class AttractionViewHolder extends BaseViewHolder<AttractionVO> {

    @BindView(R.id.tv_attraction_title)
    TextView tvAttractionTitle;

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    @BindView(R.id.tv_attraction_desc)
    TextView tvAttractionDesc;

    private ControllerAttractionItem mController;
    private AttractionVO mAttraction;

    public AttractionViewHolder(View itemView, ControllerAttractionItem controller) {
        super(itemView);
        mController = controller;
    }

    @Override
    public void onClick(View view) {
        mController.onTapAttraction(mAttraction, ivAttraction);
    }

    @Override
    public void bind(AttractionVO data) {
        mAttraction = data;
        tvAttractionTitle.setText(data.getTitle());
        tvAttractionDesc.setText(data.getDesc());

        String imageUrl = AppConstants.IMAGE_ROOT_DIR + data.getImages()[0];

        Glide.with(ivAttraction.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivAttraction);
    }

    public interface ControllerAttractionItem {
        void onTapAttraction(AttractionVO attraction, ImageView ivAttraction);
    }
}
