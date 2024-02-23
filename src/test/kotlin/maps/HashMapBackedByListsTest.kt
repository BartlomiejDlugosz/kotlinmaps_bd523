package maps

class HashMapBackedByListsTest : CustomMutableMapTest() {
    override fun <K, V> emptyMap(): CustomMutableMap<K, V> = HashMapBackedByLists<K, V>({ ListBasedMap<K, V>() })
}
