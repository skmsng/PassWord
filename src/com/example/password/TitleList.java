package com.example.password;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TitleList extends ListActivity {
	
//	ArrayList<String> list = new ArrayList<String>();
	private String category;
	
	public static final String[] COLUMNS = {"_id", "category", "title", "user", "pass", "memo"};
//	private static final String[] COLUMNS = {"category", "title", "user", "pass", "memo"};
//	private static final int[] TO = {R.id._id, R.id.category, R.id.title, R.id.user, R.id.pass, R.id.memo};
//	private static final int[] TO = {R.id.category, R.id.title, R.id.user, R.id.pass, R.id.memo};
	private DatabaseHelper dbhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        //カテゴリを受け取る
        Bundle extras = getIntent().getExtras();
		if(extras != null){
			this.category = extras.getString("category");
		}
        
        //カテゴリ別（全件）取得
        Cursor cursor = this.selectAll();
        
        //カテゴリ一覧
        //this.showGroup(cursor);
        //タイトル一覧
        this.showTitle(cursor);
        //個別詳細
        //this.showDetail(cursor);
        dbhelper.close();
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		//全件取得
		Cursor cursor = this.selectAll();
		//タイトル一覧
        this.showTitle(cursor);

        dbhelper.close();
	}
	

	//全件取得表示（タイトル）
	private Cursor selectAll() {
		//DBの準備
		this.dbhelper = new DatabaseHelper(this);
		SQLiteDatabase db = dbhelper.getReadableDatabase();	//select用
		
		Cursor cursor;
		
		//select文
		if(this.category == null){
			//query(テーブル名, カラム（列名）, where, whereパラメータ, GroupBy, Having, OrderBy)
			cursor = db.query("pw_table", TitleList.COLUMNS, null, null, null, null, null);
		}else{
			//query(テーブル名, カラム（列名）, where, whereパラメータ, GroupBy, Having, OrderBy)
			cursor = db.query("pw_table", TitleList.COLUMNS, "category="+this.category, null, null, null, null);
		}
		
//		this.startManagingCursor(cursor);
//		while(cursor.moveToNext()){
//			list.add(cursor.getString(2));
//		}
		
		return cursor;
	}
	
//	//カテゴリ一覧
//    private void showGroup(Cursor cursor) {
//    	if(cursor != null){
//    		String[] from = {"category"};
//    		int[] TO = {R.id.category};
//    		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//    				this, R.layout.category, cursor, from, TO);
//    		setListAdapter(adapter);
//    	}
//	}

    //タイトル一覧
    private void showTitle(Cursor cursor) {
    	if(cursor != null){
    		String[] from = {"title"};
    		int[] TO = {R.id.title};
    		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
    				this, R.layout.title, cursor, from, TO);
    		setListAdapter(adapter);
    	}
	}
    
//    //個別詳細
//    private void showDetail(Cursor cursor) {
//    	if(cursor != null){
//    		String[] from = {"category", "title", "user", "pass", "memo"};
//    		int[] TO = {R.id.category, R.id.title, R.id.user, R.id.pass, R.id.memo};
//    		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
//    				this, R.layout.detail, cursor, from, TO);
//    		setListAdapter(adapter);
//    	}
//    }
    
    
	//リストから１つを選択したとき
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		this.dbhelper = new DatabaseHelper(this);
		SQLiteDatabase db = this.dbhelper.getReadableDatabase();
		Cursor cursor = db.query("pw_table", TitleList.COLUMNS, "_id="+String.valueOf(id), null, null, null, null);
		
		this.startManagingCursor(cursor);
		cursor.moveToNext();
		
//		PasswordDTO dto = new PasswordDTO(
//				cursor.getInt(cursor.getColumnIndex("_id")),
//				cursor.getString(cursor.getColumnIndex("category")),
//				cursor.getString(cursor.getColumnIndex("title")),
//				cursor.getString(cursor.getColumnIndex("user")),
//				cursor.getString(cursor.getColumnIndex("pass")),
//				cursor.getString(cursor.getColumnIndex("memo"))
//				);
		
		Intent intent = new Intent(this, Password.class);
		
//		intent.putExtra("dto", dto);
		intent.putExtra("_id", cursor.getInt(cursor.getColumnIndex("_id")));
		intent.putExtra("category", cursor.getString(cursor.getColumnIndex("category")));
		intent.putExtra("title", cursor.getString(cursor.getColumnIndex("title")));
		intent.putExtra("user", cursor.getString(cursor.getColumnIndex("user")));
		intent.putExtra("pass", cursor.getString(cursor.getColumnIndex("pass")));
		intent.putExtra("memo", cursor.getString(cursor.getColumnIndex("memo")));

		startActivity(intent);
	}

	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.password, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		
		switch(item.getItemId()){
		case R.id.menu_new:
			intent = new Intent(this, Updata.class);
			startActivity(intent);
			break;
		case R.id.menu_delete:
			//delete文
			
			break;
		case R.id.menu_edit:
			//リスト名の
			break;
		case R.id.menu_back:
			//select文
			//リストへ遷移する
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
