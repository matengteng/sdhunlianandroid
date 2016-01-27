package newair.com.marriagenetwork.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import newair.com.marriagenetwork.R;
import newair.com.marriagenetwork.utils.HideActionBar;


/**
 * --------完善资料的界面-------------
 */
public class CompleteInfoActivity extends BaseActivity {

    private EditText et_name_completeActivity;     //姓名
    private EditText et_age_completeActivity;      //年龄
    private EditText et_company_completeActivity;  //单位
    private EditText et_phone_completeActivity;    //电话

    private Spinner sp_choose_sex_completeActivity;//性别选择
    private TextView sp_choose_time_completeActivity;//日期选择

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_complete_info);
        initView();
        initData();
        initEvent();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_finish_completeActivity:     //返回按钮
                finish();
                break;
            case R.id.btn_submit_completeActivity:    //提交按钮

                break;
            case R.id.sp_choose_time_completeActivity://日期选择
                DatePickerDialog dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        sp_choose_time_completeActivity.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar.hideActionBar(getSupportActionBar());

    }

    private void initView(){
        et_name_completeActivity    = (EditText) findViewById(R.id.et_name_completeActivity);
        et_age_completeActivity     = (EditText) findViewById(R.id.et_age_completeActivity);
        et_company_completeActivity = (EditText) findViewById(R.id.et_company_completeActivity);
        et_phone_completeActivity   = (EditText) findViewById(R.id.et_phone_completeActivity);

        sp_choose_sex_completeActivity  = (Spinner) findViewById(R.id.sp_choose_sex_completeActivity);
        sp_choose_time_completeActivity = (TextView) findViewById(R.id.sp_choose_time_completeActivity);

    }

    private void  initData(){
        List<String> choose_list = new ArrayList<>();
        choose_list.add("男");
        choose_list.add("女");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,choose_list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_choose_sex_completeActivity.setAdapter(adapter);

    }

    private void initEvent(){
        sp_choose_sex_completeActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
