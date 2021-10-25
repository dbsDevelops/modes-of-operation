# modes-of-operation
Sample code to demonstrate how Cipher Algorithm modes of operation work.
Input BMP Image is encrypted with DES and AES using different modes of operation (ECB and CBC).

## Requirements

* Java 11
* Maven 3.5

## Building

```
$ mvn clean package
```

## Running

```
$ java -cp target/classes es.usj.crypto.cipher.ModesOfOperationApp
```

Encrypted images will be stored in output folder specified in static `OUTPUT_FOLDER` constant from main Java class.

## Sample produced images

Original image

![original](src/main/resources/logo-usj.bmp)

DES cipher with ECB mode of operation

![des-ecb](src/main/resources/generated-images/logo-usj-DES-ECB.bmp)

AES cipher with ECB mode of operation

![aes-ecb](src/main/resources/generated-images/logo-usj-AES-ECB.bmp)

DES cipher with CBC mode of operation

![des-cbc](src/main/resources/generated-images/logo-usj-DES-CBC.bmp)

AES cipher with CBC mode of operation

![des-ecb](src/main/resources/generated-images/logo-usj-AES-CBC.bmp)
