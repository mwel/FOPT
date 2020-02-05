import java.lang.reflect.Method;

public class RMI_Skeleton {

    Object obj = ...;

    String methodName = ...;

    Object[] actualParameterList = ...;

    Class[] formalParameterList = new Class[actualParameterList.length];

    for(int i = 0; i<formalParameterList.length;i++)
    {
        formalParameterList[i] = actualParameterList[i].getClass();
    }

    Class c = obj.getClass();
    Method m = c.getMethod(methodName, formalParameterList);

    Object result = m.invoke(obj, actualParameterList);
}
