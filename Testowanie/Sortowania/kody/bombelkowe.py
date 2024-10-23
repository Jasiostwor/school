def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        # Ostatnie i elementy są już na właściwych miejscach
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1]:
                # Zamiana miejscami, jeśli element jest większy od następnego
                arr[j], arr[j+1] = arr[j+1], arr[j]

# Przykładowe użycie
if __name__ == "__main__":
    arr = [64, 34, 25, 12, 22, 11, 90]
    print("Przed sortowaniem:", arr)
    bubble_sort(arr)
    print("Po sortowaniu:", arr)
