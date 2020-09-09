import com.ProcessContainer1;
import com.nogroup.booster.processors.FunctionalProcessor;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		new FunctionalProcessor().run(ProcessContainer1.class);
	}

}
