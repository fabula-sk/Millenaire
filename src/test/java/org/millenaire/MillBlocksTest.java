import static org.junit.Assert.*;

import org.junit.Test;
import org.millenaire.blocks.MillBlocks;

public class MillBlocksTest {
    @Test
    public void testGoldOrnamentRegistryName() {
        assertNotNull("goldOrnament registry object should exist", MillBlocks.goldOrnament);
        assertEquals("gold_ornament", MillBlocks.goldOrnament.getId().getPath());
    }
}
