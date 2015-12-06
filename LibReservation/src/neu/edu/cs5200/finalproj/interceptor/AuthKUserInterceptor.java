package neu.edu.cs5200.finalproj.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import neu.edu.cs5200.finalproj.model.KUser;

public class AuthKUserInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if(session == null || session.get("user") == null) {
			return "failure";
		} else {
			return invocation.invoke();
		}
	}

}
