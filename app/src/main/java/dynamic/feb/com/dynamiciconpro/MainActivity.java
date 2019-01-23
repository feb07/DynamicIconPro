package dynamic.feb.com.dynamiciconpro;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ComponentName defaultComponent;
    private ComponentName testComponent;
    private PackageManager packageManager;
    private Button btn_change;
    private Button btn_revert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //拿到我们注册的MainActivity组件
        defaultComponent = new ComponentName(getBaseContext(), "dynamic.feb.com.dynamiciconpro.MainActivity");  //拿到默认的组件
        //拿到我注册的别名test组件
        testComponent = new ComponentName(getBaseContext(), "main.copy");

        packageManager = getApplicationContext().getPackageManager();
        btn_change = findViewById(R.id.btn_change);
        btn_revert = findViewById(R.id.btn_revert);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIcon();
            }
        });
        btn_revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDefaultIcon();
            }
        });
    }

    public void changeIcon() {
        disableComponent(defaultComponent);
        enableComponent(testComponent);
    }

    public void changeDefaultIcon() {
        enableComponent(defaultComponent);
        disableComponent(testComponent);
    }

    /**
     * 启用组件
     *
     * @param componentName
     */
    private void enableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            //已经启用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName
     */
    private void disableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            //已经禁用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
