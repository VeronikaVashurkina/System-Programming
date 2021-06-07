import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Load and operate with DLL library
 *
 * @author Veronika Vashurkina
 * @version 1.0
 */
public class LogicalAnd {
    /**
     Static initialize library
     * */
    static {
        System.loadLibrary("LogicAnd");
    }

    public LogicalAnd(){

    }
    /**
     * Native assembly method present that logical and two digit
     *
     * @param a first value
     * @param b second value
     * @return Result of logical and
     */
    private native int logAnd(int a, int b);

    /**
     * Method for calling library with using reflection
     *
     * @param a first value
     * @param b second value
     * @throws InvocationTargetException
     * @throws IllegalAccessException Execption on dowloading method
     * @throws NoSuchMethodException Wrong method name
     * @throws InstantiationException Execption of instantiation
     * @return Result of logical and
     */


    public  int getFunction(int a, int b) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {///////////////////////////////////////////////////
        Class asm = null;
        try {
            asm = Class.forName("LogicalAnd");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Logi.addLog("Работа в разделе Низкоуровневая функция: Ошибка в рефлексивной загрузке библиотеки");
        }

            Constructor constructor = asm.getConstructor();

        Method method = null;
        try {
            method = asm.getMethod("assemblerFunction", Integer.TYPE, Integer.TYPE);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Logi.addLog("Работа в разделе Низкоуровневая функция: Ошибка в рефлексивной загрузке библиотеки");
        }
        Object obj = constructor.newInstance();
        return (int) method.invoke(obj, a, b);

    }
    /**
     * Method for calling native code
     *
     * @param a first value
     * @param b second value
     * @return Result of logical and
     */
    public int assemblerFunction(int a, int b) {
        LogicalAnd assembler = new LogicalAnd();
        return assembler.logAnd(a, b);
    }
}
