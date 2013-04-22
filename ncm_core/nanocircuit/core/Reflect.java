package nanocircuit.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflect {
	public static boolean classExist(String classname) {
		Class clas;

		try {
			clas = Class.forName( classname );
		} catch( ClassNotFoundException classnotfoundexception ) {
			return false;
		}

		return true;

	}

	public static void callMethod(String classname, String methodname, Class parameters[], Object values[]) {
		Class clas;

		try {
			clas = Class.forName( classname );
		} catch( ClassNotFoundException classnotfoundexception ) {
			return;
		}

		Method method;

		try {
			method = clas.getDeclaredMethod( methodname, parameters );
		} catch( NoSuchMethodException nosuchmethodexception ) {
			return;
		}

		try {
			method.invoke( null, values );
		} catch( IllegalAccessException illegalaccessexception ) {
			return;
		} catch( InvocationTargetException invocationtargetexception ) {
			return;
		}
	}

}
