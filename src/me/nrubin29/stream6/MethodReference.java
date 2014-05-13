package me.nrubin29.stream6;

/**
 * Class used to reference methods.
 * @param <T> The return type of the referenced method.
 */
public class MethodReference<T> {

	private Object operator;
    private String method;

    /**
     * Construct a reference to a method.
     * @param operator A class containing the desired static method or an instance of a class containing the desired method.
     * @param method The name of the method to invoke.
     */
	public MethodReference(Object operator, String method) {
		this.operator = operator;
        this.method = method;
	}

	protected T access(Object... vars) throws MethodAccessException {
		Class<?> operatorClass;
		
		if (operator instanceof Class) operatorClass = (Class<?>) operator;
		else operatorClass = operator.getClass();
		
		Class<?>[] classes = new Class<?>[vars.length];
		for (int i = 0; i < vars.length; i++) {
			Class<?> clazz = vars[i].getClass();
			
			if (clazz.equals(Integer.class)) clazz = Integer.TYPE;
			else if (clazz.equals(Double.class)) clazz = Double.TYPE;
			
			classes[i] = clazz;
		}

		try {
            return (T) operatorClass.getMethod(method, classes).invoke((operator instanceof Class ? null : operator), vars);
        } catch (Exception e) {
            throw new MethodAccessException(e);
        }
	}
}