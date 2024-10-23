def partition(arr, low, high):
    pivot = arr[high]  # Wybieramy ostatni element jako pivot
    i = low - 1        # Indeks mniejszego elementu
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quick_sort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        # Rekursja na lewą i prawą stronę pivota
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

# Przykładowe użycie
if __name__ == "__main__":
    arr = [10, 7, 8, 9, 1, 5]
    print("Przed sortowaniem:", arr)
    quick_sort(arr, 0, len(arr) - 1)
    print("Po sortowaniu:", arr)
