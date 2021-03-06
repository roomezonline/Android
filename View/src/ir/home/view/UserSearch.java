package ir.home.view;

import ir.home.controller.UserController;
import ir.home.habbeh.R;
import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.view.adapter.UserAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class UserSearch extends Activity {

	private EditText userSearch;
	private Button search;
	private ListView userListView;
	private UserAdapter adapter;
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usersearch);

		final SharedPreferences sp = this.getSharedPreferences(
				"UserInformation", MODE_PRIVATE);
		userId = Integer.parseInt(sp.getString("UserId", "0"));
		userSearch = (EditText) findViewById(R.id.usersearch_edittext_userName);
		userListView = (ListView) findViewById(R.id.usersearch_list_userlistView);
		adapter = new UserAdapter(this, new ArrayList<TbUser>());
		userListView.setAdapter(adapter);

		initAllUserSearch();

	}

	private void initAllUserSearch() {
		search = (Button) findViewById(R.id.usersearch_button_userSearch);
		search.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				UserController controller = new UserController();
				try {
					List<TbUser> result = controller.Search(userSearch
							.getText().toString());

					adapter.setData(result);
					adapter.notifyDataSetChanged();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (HabehException e) {
					Toast.makeText(getBaseContext(), e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	public void onBackPressed() {
		Intent myIntent = new Intent(UserSearch.this, MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivityForResult(myIntent, 0);
		super.onBackPressed();
	}
}
