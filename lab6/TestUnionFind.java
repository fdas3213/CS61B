import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    UnionFind uf = new UnionFind(7);

    @Test
    public void testunionfind(){
        uf.union(1,2);
        uf.union(4,1);
        /* size of uf[4] should be 3 */
        assertEquals(uf.sizeOf(4),3);
        /* parent of 2 should be 1 */
        assertEquals(uf.parent(2), 1);
        /* parent of 4 should be 1 */
        assertEquals(uf.parent(4),1);
        /* root of 4 should be 1 */
        assertEquals(uf.find(4), 1);
        /* 1,2,4 should be connected */
        assertTrue(uf.connected(1,2) && uf.connected(2,4) && uf.connected(1,4));
        uf.union(3,5);
        uf.union(5,2);
        /* parent of 5 should be 3 */
        assertEquals(uf.parent(5),3);
        /* size of 5 should be 5 */
        assertEquals(uf.sizeOf(5),5);
        /* now the connected component data structure should be below array */
        int[] expected = {-1,-5,1,1,1,3,-1};
        assertArrayEquals(expected, uf.arr);
    }
}
