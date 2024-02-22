package maps

class ListBasedMapTest : CustomMutableMapTest() {
    override fun <K, V> emptyMap(): CustomMutableMap<K, V> = ListBasedMap()
}
