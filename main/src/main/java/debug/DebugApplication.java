package debug;

import com.gavel.core.CoreApplication;
import com.gavel.main.BuildConfig;

/**
 * Created by jiahua on 17-2-22.
 * Description：
 */

public class DebugApplication extends CoreApplication
{

    @Override
    protected boolean isDebug()
    {
        return BuildConfig.DEBUG;
    }
}
