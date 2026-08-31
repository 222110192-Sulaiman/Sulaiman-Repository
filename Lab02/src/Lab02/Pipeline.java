package Lab02;
import java.util.List;
import java.util.ArrayList;


public class Pipeline<T, R> {
	
	private List<Transformer<?, ?>> transformers;

	public Pipeline() {
		transformers = new ArrayList<>();
	}
	public <V> Pipeline<T, V> addTransformer(Transformer<R, V> transformer){
		
		Pipeline<T, V> newPipeline = new Pipeline<>();
		newPipeline.transformers.addAll(this.transformers);
		newPipeline.transformers.add(transformer);
		
		return newPipeline;
	}
	@SuppressWarnings("Unchecked")
public R execute(T input) {
	Object result = input;
	for (Transformer<?, ?> transformer : transformers) {
		Transformer<Object, Object> t = 
				(Transformer<Object, Object>) transformer;
		result = t.Transform(result);
	
	}
	return (R) result;
}

}
