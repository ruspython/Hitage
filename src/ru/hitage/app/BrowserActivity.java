package ru.hitage.app;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class BrowserActivity extends Activity {
	private Document doc= null;
	TextView nodeView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.browser);   
	    nodeView = (TextView) findViewById(R.id.nodeView);
		Uri data = getIntent().getData();

	    try {
			doc = Jsoup.connect(data.toString()).get();
		} catch (IOException e) {e.printStackTrace();}
	    String text = doc.getElementsByAttributeValueStarting("class", "content clear-block").html();
	    nodeView.setText(Html.fromHtml(text));
	    nodeView.setMovementMethod(new ScrollingMovementMethod());
	    
	    String imageLink = doc.getElementsByAttributeValueStarting("class", "content clear-block").get(0).getElementsByTag("img").attr("src").toString();
	    Bitmap image = null;
		try {
			URL imageURL = new URL("http://hitage.ru"+imageLink);
			image = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ImageView imageView = (ImageView) findViewById(R.id.imageView);
	    imageView.setImageBitmap(image);

	}
}
