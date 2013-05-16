package ru.hitage.app;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
    private Document doc= null;
    private String link,node;
    private ArrayList<String> linkList = new ArrayList<String>();
    private ListView listView;
    
	@Override
	public void onCreate(Bundle savedInstanceState)       
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);    

	    try {
			doc = Jsoup.connect("http://hitage.ru").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Elements nodes = doc.getElementsByAttributeValueStarting("class", "page node");
	    
	    listView = (ListView)findViewById(R.id.listView1);  
	   
	    ArrayList<String> hm = new ArrayList<String>();

	    for (Element e:nodes){
	    	
	    	link = e.getElementsByAttributeValueStarting("href", "/content").attr("href");
	    	linkList.add(link);
	    	node = e.getElementsByTag("h2").text();    
	    	hm.add(node);
	
	    }

	    Animation anim = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.fade_in);
		listView.startAnimation(anim);	
	    
	    ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.list, R.id.title,hm);   
	            
	    listView.setAdapter(adapter);                        
	    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
	
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hitage.ru"+linkList.get(position))));
				
			}
		});
	
	
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
		 	getMenuInflater().inflate(R.menu.main_menu, menu);
			return super.onCreateOptionsMenu(menu);
	    }

	

	@Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			switch (item.getItemId()){
			case R.id.author:
				Intent intent = new Intent(this, Author.class);
				startActivity(intent);
				break;
			case R.id.visit: startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hitage.ru"))); break;
			case R.id.exit : finish(); break;
			}
			return false;
			
		}

}


