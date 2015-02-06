package kr.co.dnasoft.remontest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SampleActivity extends Activity implements OnItemClickListener {
	
	 private ArrayList<ActivityItem> activityList = new ArrayList<ActivityItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setActivityItem();
		
		LinearLayout layout = new LinearLayout(this);

        ListView activityListView = new ListView(this);
        activityListView.setAdapter(new ActivityItemAdapter(activityList));
        activityListView.setOnItemClickListener(this);

        layout.addView(activityListView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        setContentView(layout);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		this.activityList.get(position).onclick();
	}
	
	 public void setActivityItem() {
         activityList.add(new ActivityItem("Inline Banner Type", JavaTypeInlineBanner.class));
         activityList.add(new ActivityItem("Rectangle Banner Type", JavaTypeRectangleBanner.class));
         activityList.add(new ActivityItem("Square Banner Type", JavaTypeSquareBanner.class));
         activityList.add(new ActivityItem("Interstitial Banner Type", JavaTypeInterstitialBanner.class));
         activityList.add(new ActivityItem("Floating Banner Type", JavaTypeFloatingBanner.class));
         activityList.add(new ActivityItem("Agree Sample", AgreeSampleActivity.class));
    }
	
	private class ActivityItem {
        protected String activityName;
        protected Class<?> cls;

        public ActivityItem(String name, Class<?> cls) {
            this.activityName = name;
            this.cls = cls;
        }

        public String getName() {
            return this.activityName;
        }

        public void onclick() {
            if ( cls == null ) {
                return;
            }

            Intent intent = new Intent(SampleActivity.this, cls);
            startActivity(intent);
        }
    }

	private class ActivityItemAdapter extends BaseAdapter {

        private ArrayList<ActivityItem> activityItemList;

        public ActivityItemAdapter(ArrayList<ActivityItem> list) {
        	activityItemList = list;
        }

        public int getCount() {
            return activityItemList.size();
        }

        public Object getItem(int position) {
            return activityItemList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            
        	View v = convertView;
            
        	if (v == null) {
                v = View.inflate(SampleActivity.this, R.layout.activity_item, null);
            }
        	
            ActivityItem activityItem = activityItemList.get(position);
            
            if (activityItem != null) {
                TextView tv = (TextView) v.findViewById(R.id.row);
                tv.setText(activityItem.getName());
            }
            
            return v;
        }

    }
	
}
