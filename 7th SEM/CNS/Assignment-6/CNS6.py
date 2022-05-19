import numpy
import math

def find_key_matrix(key, n):
	key_matrix = []
	k=0
	for _ in range(n):
		row = []
		for __ in range(n):
			row.append(ord(key[k]) - 65)
			k+=1
		key_matrix.append(row)
	return key_matrix

def encrypt(text, key):
	n = int(int(len(key))**0.5)
	encryptedPieces = []
	cipher = ''
	key_matrix = find_key_matrix(key,n)
	l = len(text)
	extra = 0
	if l % n != 0:
		for _ in range(n - (l % n)):
			extra += 1
			text += 'Z'
	i=0
	l = len(text)
	while i<l:
		piece = text[i:i+n]
		column_vector = []
		for x in piece:
			column_vector.append([ord(x)-65])
		encryptedPieces.append(numpy.dot(key_matrix, column_vector))
		for row in numpy.dot(key_matrix, column_vector):
			cipher += chr((int(row[0]) + 26) % 26 + 65)
		i += n

	return [cipher, encryptedPieces, extra]

def decrypt(cipher, key, encryptedPieces, extra):
	n = int(int(len(key))**0.5)
	text = ''
	key_matrix = find_key_matrix(key, n)
	inverse_key_matrix = numpy.linalg.inv(key_matrix)
	l = len(cipher)
	if l % n != 0:
		for _ in range(n - (l % n)):
			cipher += 'Z'
	i=0
	k=0
	l = len(cipher)
	while i<l:
		piece = cipher[i:i+n]
		column_vector = []
		for x in piece:
			column_vector.append([ord(x)-65])
		for row in numpy.dot(inverse_key_matrix, encryptedPieces[k]):
			text += chr(round(math.fmod(row[0], 26)) + 65)
		i += n
		k+=1

	return text[0 : len(text) - extra]

def main():
	encryptedPieces = []
	extra = 0
	while True:
		choice = input('1) Encrypt\n2) Decrypt\n3) Exit\n')
		if choice == '1':
			fin = open('input6.txt', 'r')
			fout = open('output6.txt', 'w')
			text = fin.read()
			print(f'Plain Text is: {text}')

			key = ''
			while True:
				key = input('Enter the key: ')
				n=len(key)

				if (n**0.5)*(n**0.5) != n or numpy.linalg.det(find_key_matrix(key, int(n**0.5))) == 0.0:
					print('Enter valid key(Key length needs to be perfect square and key matrix should be invertible)')
				else:
					break
			text = text.upper()
			key = key.upper()
			[cipher, encryptedPieces, extra] = encrypt(text, key)

			print(f'Cipher is: {cipher}\n\n')
			fout.write(cipher)

			fin.close()
			fout.close()

		elif choice == '2':
			fin = open('output6.txt', 'r')
			fout = open('input6.txt', 'w')
			cipher = fin.read()
			print(f'Cipher is: {cipher}')
			key = ''
			while True:
				key = input('Enter the key: ')
				n=len(key)

				if (n**0.5)*(n**0.5) != n or numpy.linalg.det(find_key_matrix(key, int(n**0.5))) == 0.0:
					print('Enter valid key(Key length needs to be perfect square and key matrix should be invertible)')
				else:
					break

			cipher = cipher.upper()
			key = key.upper()
			text = decrypt(cipher, key, encryptedPieces, extra)

			print(f'Plain Text is: {text}\n\n')
			fout.write(text)

			fin.close()
			fout.close()

		elif choice == '3':
			print('Thanks!\n')
			return

		else:
			print('Invalid Choice\n\n')

if __name__ == '__main__':
	main()