Platform Engineer test
=================================

The PEt is located at:
http://ec2-46-137-139-56.eu-west-1.compute.amazonaws.com:9000/

This page will show the last received WebSocket message


To start the server:
ssh to  `ubuntu@ec2-46-137-139-56.eu-west-1.compute.amazonaws.com` with your provided key.

The project is already cloned in the directory.

`cd  activator/pet`

`./activator run`

REST endpoints
=================================

Dummy input

* **URL**

  /dummy-input

* **Method:**
  
  `POST` 

* **Data Params**

  { name : [string] }

* **Code:** 200 <br />
    **Content:** `ok`

Get persisted messages

* **URL**

  /persisted

* **Method:**
  
  `GET` 
* **Success Response:**

* **Code:** 200 <br />
    **Content:** `{ persisted-data : [List of persisted messages] }`

