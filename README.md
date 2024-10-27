# Modes of Operation in Cryptography

The project is based on a previous implementation done by my Cryptography tutor [angelborroy](https://github.com/angelborroy).

This project demonstrates how different cipher algorithm **modes of operation** work in cryptography. 

Specifically, it showcases the encryption of an input BMP image using **DES** and **AES** ciphers with the **ECB (Electronic Code Book)** and **CBC (Cipher Block Chaining)** modes of operation.

Modes of operation define how a block cipher encrypts data. The two modes covered in this project are:

- **ECB (Electronic Code Book)**: Each block of data is encrypted independently, which can result in patterns from the plaintext being visible in the ciphertext (especially for images).
- **CBC (Cipher Block Chaining)**: Each block of data is XORed with the previous block before encryption, providing better security by eliminating identical ciphertext blocks for identical plaintext blocks.

The project encrypts an input BMP image and produces new BMP files that show the effects of encryption using these modes of operation.

## Requirements

To run this project, ensure that you have the following software installed:
- **Java 11** or later
- **Maven 3.5** or later

## Building the Project

To build the project, run the following command from the project root:

```bash
$ mvn clean package
```

This will compile the code and package it into a runnable format.

## Running the Project

After building the project, you can run it with the following command:

```bash
$ java -cp target/classes es.usj.crypto.cipher.ModesOfOperationApp
```

This will execute the encryption process on the input BMP file located in the `resources` folder. The project encrypts the image using both DES and AES with ECB and CBC modes of operation.

### Output Folder

The encrypted images will be stored in the output folder specified by the `OUTPUT_FOLDER` constant in the main Java class (`ModesOfOperationApp`). You can modify this folder location to suit your system by updating the `OUTPUT_FOLDER` value.

## Sample Produced Images

Here are some sample images produced by the project, showing the original image and the images encrypted with different cipher modes:

### Original Image

The input BMP image before encryption:

![original](src/main/resources/logo-usj.bmp)

### Encrypted Images

#### DES Cipher with ECB Mode of Operation

In ECB mode, each block of the image is encrypted independently, resulting in noticeable visual patterns when encrypting images:

![des-ecb](src/main/resources/generated-images/logo-usj-DES-ECB.bmp)

#### AES Cipher with ECB Mode of Operation

AES is a stronger cipher than DES, but ECB mode still shows patterns, which can make it unsuitable for some applications:

![aes-ecb](src/main/resources/generated-images/logo-usj-AES-ECB.bmp)

#### DES Cipher with CBC Mode of Operation

In CBC mode, each block depends on the encryption of the previous block, making the resulting encrypted image more secure and less predictable:

![des-cbc](src/main/resources/generated-images/logo-usj-DES-CBC.bmp)

#### AES Cipher with CBC Mode of Operation

AES combined with CBC mode results in a more secure encryption, and no discernible patterns are visible in the output image:

![aes-cbc](src/main/resources/generated-images/logo-usj-AES-CBC.bmp)

## Alternative: Run your project on Docker! 🐳

We will create a Dockerfile following the next steps
1. Start from a Maven Docker Image, we will use the default one
2. Clone the project
3. Run Maven to compile the project and download dependencies
4. Create the container folder to store the output pictures (taken from Java source code)
5. Run the application (according to README.md instructions)

```bash
# 1. Start from a Maven Docker Image
FROM maven AS build

# 2. Clone the project
RUN git clone https://github.com/dbsDevelops/modes-of-operation.git

# 3. Access our project and run Maven to compile the project and download dependencies
RUN cd modes-of-operation && mvn clean package

# 4. Create the container folder to store the ouput pictures (taken from Java source code)
RUN mkdir -p /Users/danielbuxtonsierras/tmp 

# 5. Run the application (according to README.md instructions)
ENTRYPOINT ["java", "-cp", "/app/modes-of-operation/target/classes", "es.usj.crypto.cipher.ModesOfOperationApp"]
```

Once the Dockerfile is ready, build the Docker Image from your computer:
```bash
$ docker build . -t modes-of-operation
```

Run the Docker Image mapping the container output folder to a local folder in your computer (in the following sample, the mapping is using a local folder "output" as relative path):
```bash
$ docker run -v ./output:/Users/danielbuxtonsierras/tmp modes-of-operation
```

Finally, verify that the output images are stored in your local computer:
```bash
$ ls -l
total 5120
drwxr-xr-x  2 danielbuxtonsierras  staff      64 Oct 27 20:08 decrypt
-rw-r--r--  1 danielbuxtonsierras  staff  323606 Oct 27 20:08 decryptlogo-usj-AES-CBC.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323622 Oct 27 20:08 decryptlogo-usj-AES-ECB.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323622 Oct 27 20:08 decryptlogo-usj-DES-CBC.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323630 Oct 27 20:08 decryptlogo-usj-DES-ECB.bmp
drwxr-xr-x  2 danielbuxtonsierras  staff      64 Oct 27 20:08 encrypt
-rw-r--r--  1 danielbuxtonsierras  staff  323670 Oct 27 20:08 encryptlogo-usj-AES-CBC.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323654 Oct 27 20:08 encryptlogo-usj-AES-ECB.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323654 Oct 27 20:08 encryptlogo-usj-DES-CBC.bmp
-rw-r--r--  1 danielbuxtonsierras  staff  323646 Oct 27 20:08 encryptlogo-usj-DES-ECB.bmp
```
