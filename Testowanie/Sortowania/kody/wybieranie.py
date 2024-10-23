def selection_sort(arr):
    for i in range(len(arr)):
        # Znalezienie najmniejszego elementu w nieposortowanej części tablicy
        min_idx = i
        for j in range(i+1, len(arr)):
            if arr[j] < arr[min_idx]:
                min_idx = j
        # Zamiana miejscami elementu minimalnego z pierwszym elementem
        arr[i], arr[min_idx] = arr[min_idx], arr[i]

# Przykładowe użycie
if __name__ == "__main__":
    arr = [64, 25, 12, 22, 11]
    print("Przed sortowaniem:", arr)
    selection_sort(arr)
    print("Po sortowaniu:", arr)
