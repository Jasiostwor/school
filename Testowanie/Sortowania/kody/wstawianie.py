def insertion_sort(arr):
    # Przechodzimy przez kolejne elementy zaczynając od drugiego
    for i in range(1, len(arr)):
        key = arr[i]  # Aktualny element do wstawienia
        j = i - 1
        # Porównujemy element kluczowy z poprzednimi elementami
        # i przesuwamy większe elementy w prawo
        while j >= 0 and key < arr[j]:
            arr[j + 1] = arr[j]
            j -= 1
        # Wstawiamy klucz na odpowiednie miejsce
        arr[j + 1] = key

# Przykładowe użycie
if __name__ == "__main__":
    arr = [12, 11, 13, 5, 6]
    print("Przed sortowaniem:", arr)
    insertion_sort(arr)
    print("Po sortowaniu:", arr)
