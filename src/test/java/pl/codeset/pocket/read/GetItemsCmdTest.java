package pl.codeset.pocket.read;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GetItemsCmdTest {

    @Test
    void invalidCountValue() {
        assertThrows(IllegalArgumentException.class, () -> new GetItemsCmd.Builder().count(-1));
        assertThrows(IllegalArgumentException.class, () -> new GetItemsCmd.Builder().count(31));
    }

    @Test
    void invalidOffsetValue() {
        assertThrows(IllegalArgumentException.class, () -> new GetItemsCmd.Builder().offset(-1));
    }

    @Test
    void invalidTotalValue() {
        assertThrows(IllegalArgumentException.class, () -> new GetItemsCmd.Builder().total(-1));
    }
}
