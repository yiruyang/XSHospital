

package com.example.a12878.xshospital.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public class Collections {
    public static final List EMPTY_LIST = null;
    public static final Map EMPTY_MAP = null;
    public static final Set EMPTY_SET = null;

    Collections() {
        throw new RuntimeException("Stub!");
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        throw new RuntimeException("Stub!");
    }

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        throw new RuntimeException("Stub!");
    }

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        throw new RuntimeException("Stub!");
    }

    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c) {
        throw new RuntimeException("Stub!");
    }

    public static void reverse(List<?> list) {
        throw new RuntimeException("Stub!");
    }

    public static void shuffle(List<?> list) {
        throw new RuntimeException("Stub!");
    }

    public static void shuffle(List<?> list, Random rnd) {
        throw new RuntimeException("Stub!");
    }

    public static void swap(List<?> list, int i, int j) {
        throw new RuntimeException("Stub!");
    }

    public static <T> void fill(List<? super T> list, T obj) {
        throw new RuntimeException("Stub!");
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        throw new RuntimeException("Stub!");
    }

    public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        throw new RuntimeException("Stub!");
    }

    public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
        throw new RuntimeException("Stub!");
    }

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        throw new RuntimeException("Stub!");
    }

    public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
        throw new RuntimeException("Stub!");
    }

    public static void rotate(List<?> list, int distance) {
        throw new RuntimeException("Stub!");
    }

    public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) {
        throw new RuntimeException("Stub!");
    }

    public static int indexOfSubList(List<?> source, List<?> target) {
        throw new RuntimeException("Stub!");
    }

    public static int lastIndexOfSubList(List<?> source, List<?> target) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Set<T> unmodifiableSet(Set<? extends T> s) {
        throw new RuntimeException("Stub!");
    }

    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s) {
        throw new RuntimeException("Stub!");
    }

    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> m) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Collection<T> synchronizedCollection(Collection<T> c) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Set<T> synchronizedSet(Set<T> s) {
        throw new RuntimeException("Stub!");
    }

    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s) {
        throw new RuntimeException("Stub!");
    }

    public static <T> List<T> synchronizedList(List<T> list) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> m) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m) {
        throw new RuntimeException("Stub!");
    }

    public static <E> Collection<E> checkedCollection(Collection<E> c, Class<E> type) {
        throw new RuntimeException("Stub!");
    }

    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
        throw new RuntimeException("Stub!");
    }

    public static <E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type) {
        throw new RuntimeException("Stub!");
    }

    public static <E> List<E> checkedList(List<E> list, Class<E> type) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> Map<K, V> checkedMap(Map<K, V> m, Class<K> keyType, Class<V> valueType) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> SortedMap<K, V> checkedSortedMap(SortedMap<K, V> m, Class<K> keyType, Class<V> valueType) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Iterator<T> emptyIterator() {
        throw new RuntimeException("Stub!");
    }

    public static <T> ListIterator<T> emptyListIterator() {
        throw new RuntimeException("Stub!");
    }

    public static <T> Enumeration<T> emptyEnumeration() {
        throw new RuntimeException("Stub!");
    }

    public static final <T> Set<T> emptySet() {
        throw new RuntimeException("Stub!");
    }

    public static final <T> List<T> emptyList() {
        throw new RuntimeException("Stub!");
    }

    public static final <K, V> Map<K, V> emptyMap() {
        throw new RuntimeException("Stub!");
    }

    public static <E> Set<E> singleton(E o) {
        throw new RuntimeException("Stub!");
    }

    public static <E> List<E> singletonList(E o) {
        throw new RuntimeException("Stub!");
    }

    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        throw new RuntimeException("Stub!");
    }

    public static <T> List<T> nCopies(int n, T o) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Comparator<T> reverseOrder() {
        throw new RuntimeException("Stub!");
    }

    public static <T> Comparator<T> reverseOrder(Comparator<T> cmp) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Enumeration<T> enumeration(Collection<T> c) {
        throw new RuntimeException("Stub!");
    }

    public static <T> ArrayList<T> list(Enumeration<T> e) {
        throw new RuntimeException("Stub!");
    }

    public static int frequency(Collection<?> c, Object o) {
        throw new RuntimeException("Stub!");
    }

    public static boolean disjoint(Collection<?> c1, Collection<?> c2) {
        throw new RuntimeException("Stub!");
    }

    @SafeVarargs
    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        throw new RuntimeException("Stub!");
    }

    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        throw new RuntimeException("Stub!");
    }

    public static <T> Queue<T> asLifoQueue(Deque<T> deque) {
        throw new RuntimeException("Stub!");
    }
}
