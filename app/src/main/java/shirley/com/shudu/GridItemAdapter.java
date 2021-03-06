package shirley.com.shudu;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class GridItemAdapter extends BaseAdapter {

    private List<GridItem> gridItemList;
    private Context context;
    private LayoutInflater mInflater;
    private int selection;

    public GridItemAdapter(Context context, List<GridItem> objects,int selection) {
        gridItemList = objects;
        this.context = context;
        this.selection = selection;
    }

    @Override
    public int getCount() {
        return gridItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return gridItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(mInflater == null){
            mInflater = LayoutInflater.from(context);
        }
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.layout_item_griditem, null);
            holder = new ViewHolder();
            holder.tv_content = (TextView)convertView.findViewById(R.id.item_tv_gridcell);
            convertView.setTag(holder);
            holder.tv_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return false;
                }
            });
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        GridItem item = gridItemList.get(position);
        //不为0将数字显示，为0将格子初始化
        if(item.num != 0) {
            holder.tv_content.setText(String.valueOf(item.num));
        }
        else{
            holder.tv_content.setText(null);
        }
        if(item.isFix) {
            holder.tv_content.setEnabled(false);
            holder.tv_content.setTextColor(context.getResources().getColor(R.color.fix_textcolor));
        }
        else{
            holder.tv_content.setEnabled(true);
            holder.tv_content.setTextColor(context.getResources().getColor(R.color.fill_textcolor));
        }
        if(item.isSame){
            holder.tv_content.setBackgroundColor(context.getResources().getColor(R.color.item_conflict_back));
        }
        else {
            if (item.isSelected) {
                if(position == selection){
                    holder.tv_content.setBackgroundResource(R.color.item_select_back);
                }
                else {
                    holder.tv_content.setBackgroundResource(R.color.item_select_reference_back);
                }
            } else {
                holder.tv_content.setBackgroundResource(R.color.item_back);
            }
        }
        return convertView;
    }

    public  List<GridItem> getUserResult(){
        return gridItemList;
    }

    public void setSelection(int selection){
        this.selection = selection;
    }

    class ViewHolder{
        public TextView tv_content;
    }

}
