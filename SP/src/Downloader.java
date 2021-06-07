import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;

/***
 *The class allows you to load the code entered by the user for execution
 *  @author Veronika Vadhurkina
 *  @version 1.0
 */
public class Downloader {
    /**The name of the class in which the user code is placed*/
    private static final String CLASS_NAME = "MyClass";

    /***
     * Method loads user code for execution
     * @param code user code
     * @throws Exception Wrong load user code
     */
    public static void task(String code) throws Exception {
//загружаем компилятор
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        String str = "{ flag++;";
        int index = code.indexOf('o');
        StringBuffer sb = new StringBuffer(code);
        sb.insert(index + 1, str);

        code = String.valueOf(sb);

        String str1 = " }";
        index = code.indexOf('w');
        sb = new StringBuffer(code);
        sb.insert(index - 1, str1);

        code = String.valueOf(sb);




        String newCode = "" +
                "public class MyClass {" +
                " public static void main(String[] args) {" +
                "int flag =0;" +
                code +
                "Presenter.setLabelResult(flag);" +
                " }" +
                "}";

//исходный код
        SourceCode sourceCode = new SourceCode(CLASS_NAME, newCode);

//скомпилированный байт-код
        CompiledCode compiledCode = new CompiledCode(CLASS_NAME);

//переопределяем метод поиска класса для загрузки
        ClassLoader classLoader = new ClassLoader(ClassLoader.getSystemClassLoader()) {
            @Override
            protected Class<?> findClass(String name) {
//записывем байт-код откомпилированного класса
                byte[] byteCode = compiledCode.getByteCode();
//получаем представление класса
                return defineClass(name, byteCode, 0, byteCode.length);
            }
        };

//данные только в оперативной памяти
        JavaFileManager fileManager = new ForwardingJavaFileManager<>(compiler.getStandardFileManager(null, null, null)) {
            //в качестве выходного файла будет вызван наш объект
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
                return compiledCode;
            }

            @Override
            public ClassLoader getClassLoader(Location location) {
                return classLoader;
            }
        };

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singletonList(sourceCode));

        if (task.call()) {
            Class<?> clazz = classLoader.loadClass(CLASS_NAME);
            clazz.getDeclaredMethod("main", String[].class).invoke(null, new Object[]{null});
        }
    }


    /**
     * A class for storing Java source code
     */
    static class SourceCode extends SimpleJavaFileObject {
        private String code;

        SourceCode(String className, String code) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    /**
     * Class for storing compiled bytecode
     */
    static class CompiledCode extends SimpleJavaFileObject {
        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        CompiledCode(String className) throws Exception {
            super(new URI(className), Kind.CLASS);
        }

        @Override
        public OutputStream openOutputStream() {
            return baos;
        }

        byte[] getByteCode() {
            return baos.toByteArray();
        }
    }
}

