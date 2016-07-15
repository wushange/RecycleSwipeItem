package com.wsg.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsg.demo.R;
import com.wsg.demo.SwipeItemOnItemChildClickListener;
import com.wsg.demo.data.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;

public class IndexAdapter extends BaseAdapter implements SectionIndexer,
        Filterable {
    private MemberHandler userHandler;
    private List<Person> members;
    private LayoutInflater inflater;
    private Context context;
    SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener = null;

    protected OnItemClickListener mItemClickListener;
    protected OnItemLongClickListener mItemLongClickListener;

    public IndexAdapter(Context context, List<Person> members) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.members = members;
    }

    public void setSwipeItemOnItemChildClickListener(SwipeItemOnItemChildClickListener swipeItemOnItemChildClickListener) {
        this.swipeItemOnItemChildClickListener = swipeItemOnItemChildClickListener;
    }

    public void updateDatas(List<Person> members) {
        this.members = members;
        notifyDataSetChanged();
    }

    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    @Override
    public int getCount() {
        return members != null ? members.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if (members != null && position > 0 && position < members.size()) {
            return members.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (members != null && position > 0 && position < members.size()) {
            return position;
        }
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {

            final Person member = members.get(position);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_index_person,
                        null);
                userHandler = new MemberHandler();
                userHandler.mTv_name = (TextView) convertView.findViewById(R.id.person_name);
                userHandler.mTv_sign = (TextView) convertView.findViewById(R.id.person_sign);
                userHandler.mImg_face = (ImageView) convertView.findViewById(R.id.person_face);
                userHandler.tag = (TextView) convertView.findViewById(R.id.tag);
                userHandler.tv_item_swipe_delete = (TextView) convertView.findViewById(R.id.tv_item_swipe_delete);
                userHandler.tv_item_swipe_detial = (TextView) convertView.findViewById(R.id.tv_item_swipe_detial);
                userHandler.itemLayout = (BGASwipeItemLayout) convertView.findViewById(R.id.sil_item_swipe_root);
                convertView.setTag(userHandler);
            } else {
                userHandler = (MemberHandler) convertView.getTag();

            }
            setSwipeClickLisener(position);
            userHandler.mTv_name.setText(member.getName());
            userHandler.mTv_sign.setText(member.getSign());
            Glide.with(context)
                    .load(member.getFace())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(userHandler.mImg_face);
            userHandler.itemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
                @Override
                public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                    closeOpenedSwipeItemLayoutWithAnim();
                    mOpenedSil.add(swipeItemLayout);
                }

                @Override
                public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                    mOpenedSil.remove(swipeItemLayout);
                }

                @Override
                public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                    closeOpenedSwipeItemLayoutWithAnim();
                }
            });
            // 判断当前item 的首字母和 上一个item的首字母是否相同
            boolean flag = false;
            String curLetter = member.getFirstLetter();
            if (0 == position) {
                if (curLetter != null)
                    flag = true;
            } else {
                String lastLetter = members.get(position - 1).getFirstLetter();
                if (curLetter != null && lastLetter != null) {
                    if (!curLetter.equals(lastLetter)) {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
            }

            if (flag) {//如果相同就隐藏字母
                userHandler.tag.setVisibility(View.VISIBLE);
                userHandler.tag.setText(member.getFirstLetter());
            } else {
                userHandler.tag.setVisibility(View.GONE);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        //itemView 的点击事件
        if (mItemClickListener != null) {
            userHandler.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }

        if (mItemLongClickListener != null) {
            userHandler.itemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.onItemClick(position);
                }
            });
        }
        return convertView;
    }

    private void setSwipeClickLisener(final int position) {
        userHandler.tv_item_swipe_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != swipeItemOnItemChildClickListener) {
                    swipeItemOnItemChildClickListener.onItemChildClick(null, view, position);
                }

            }
        });
        userHandler.tv_item_swipe_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return null != swipeItemOnItemChildClickListener ? swipeItemOnItemChildClickListener.onItemChildLongClick(null, view, position) : false;
            }
        });
        userHandler.tv_item_swipe_detial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != swipeItemOnItemChildClickListener) {
                    swipeItemOnItemChildClickListener.onItemChildClick(null, view, position);
                }
            }
        });
        userHandler.tv_item_swipe_detial.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return null != swipeItemOnItemChildClickListener ? swipeItemOnItemChildClickListener.onItemChildLongClick(null, view, position) : false;
            }
        });
    }


    class MemberHandler {
        TextView mTv_name;
        ImageView mImg_face;
        TextView mTv_sign;
        TextView tv_item_swipe_delete;
        TextView tv_item_swipe_detial;
        BGASwipeItemLayout itemLayout;
        TextView tag;
    }

    private int mLastPosition = -1;


    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < members.size(); i++) {
            String l = members.get(i).firstLetter;

            if (l.length() <= 0) {
                l = "0";
            }

            char firstChar = l.toUpperCase().charAt(0);

            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new UserFilter(members);
        return filter;
    }

    UserFilter filter;

    public Person getUserForIndex(int index) {
        return members.get(index);
    }

    public class UserFilter extends Filter {

        private List<Person> list;

        public UserFilter(List<Person> list) {
            this.list = list;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = list;
                results.count = list.size();
            } else {
                List<Person> mList = new ArrayList<Person>();
                for (Person person : members) {
                    if (!person.name.isEmpty()
                            && person.name.toUpperCase().contains(
                            constraint.toString().toUpperCase())
                            ) {
                        mList.add(person);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence arg0, FilterResults results) {
            members = (List<Person>) results.values;
            notifyDataSetChanged();
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemClick(int position);
    }

}