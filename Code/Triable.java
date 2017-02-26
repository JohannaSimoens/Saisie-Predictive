/**
 * 
 * interface des objets qui sont triables,c'est à dire comparable et clonable 
 *
 * @param <T>
 */
public interface Triable <T> extends Comparable<T>, Cloneable{

	Triable<T> clone();
}
