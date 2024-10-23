def merge(arr, left, middle, right):
    n1 = middle - left + 1
    n2 = right - middle

    L = arr[left:middle+1]
    R = arr[middle+1:right+1]

    i = j = 0
    k = left

    while i < n1 and j < n2:
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    while i < n1:
        arr[k] = L[i]
        i += 1
        k += 1

    while j < n2:
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sort(arr, left, right):
    if left < right:
        middle = left + (right - left) // 2
        # Rekursja na lewą i prawą część tablicy
        merge_sort(arr, left, middle)
        merge_sort(arr, middle + 1, right)
        merge(arr, left, middle, right)

# Przykładowe użycie
if __name__ == "__main__":
    arr = [12, 11, 13, 5, 6, 7]
    print("Przed sortowaniem:", arr)
    merge_sort(arr, 0, len(arr) - 1)
    print("Po sortowaniu:", arr)
