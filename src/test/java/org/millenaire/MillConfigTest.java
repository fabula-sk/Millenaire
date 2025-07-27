import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.millenaire.MillConfig;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class MillConfigTest {
    @Test
    public void testDefaultNameDistance() throws Exception {
        Field f = MillConfig.class.getDeclaredField("nameDistanceVal");
        f.setAccessible(true);
        IntValue val = (IntValue) f.get(null);
        assertEquals(20, val.get());
    }
}
