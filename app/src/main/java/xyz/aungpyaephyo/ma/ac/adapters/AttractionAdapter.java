package xyz.aungpyaephyo.ma.ac.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.aungpyaephyo.ma.ac.R;
import xyz.aungpyaephyo.ma.ac.data.vo.AttractionVO;
import xyz.aungpyaephyo.ma.ac.viewholders.AttractionViewHolder;


/**
 * Created by aung on 7/6/16.
 */
public class AttractionAdapter extends BaseRecyclerAdapter<AttractionViewHolder, AttractionVO> {

    private AttractionViewHolder.ControllerAttractionItem mControllerAttractionItem;

    public AttractionAdapter(Context context, AttractionViewHolder.ControllerAttractionItem controllerAttractionItem) {
        super(context);
        mControllerAttractionItem = controllerAttractionItem;
    }

    @Override
    public AttractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_attraction, parent, false);
        return new AttractionViewHolder(itemView, mControllerAttractionItem);
    }

    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }
}
