Icecat importer
===============

This is the icecat-importer free program developed by Christian Roman Rua.
Version: 1.0

Disclaimer
===========
This program was developed for the sake of having fun with my new knowledge acquired about Java 7 and also because I found it hard to find a
simple program that could import icecat products to a Database.

Because this program was developed just for the fun of developing in Java, use it at your own risk and if your computer or company building does not explode
enjoy it :D

Introduction
============

This program was developed with the intention to make it easy the import of ICECAT products.
Also this project was developed with the intention to apply all the new knowledge that I acquired about Java 7, JAXB and Hibernate.

Icecat is a product catalog with more than two million product data-sheets of 7000+ brands in 35 different languages. 
For more information about ICECAT: https://us.icecat.biz/

To import Icecat products, you need to create an Icecat account, because to make this program work, you will need them.
You also need a MySQL database to import the information of the Icecat products imported.

Important Notes:
- This program only works with Open Icecat, though if you have a paid account in Icecat you can set a URL different than: 
	https://data.icecat.biz/export/freexml
  and the program should work, though this was not tested.
- This program use many features of Java 7, so you will need it to make it run.

Database
=========
This program will import the Icecat information to your database.
You don't need to create the tables, this program takes care of it.
The tables created will be:

+-----------------------------------+
| Tables_in_icecat                  |
+-----------------------------------+
| Category                          |
| CategoryFeatureGroup              |
| CategoryFeatureGroup_FeatureGroup |
| Category_Name                     |
| Feature                           |
| FeatureGroup                      |
| FeatureGroup_Name                 |
| Feature_Name                      |
| Name                              |
| Product                           |
| ProductDescription                |
| ProductFeature                    |
| ProductFeature_Feature            |
| ProductRelated                    |
| Product_Category                  |
| Product_CategoryFeatureGroup      |
| Product_ProductDescription        |
| Product_ProductFeature            |
| Product_ProductRelated            |
| Supplier                          |
+-----------------------------------+

From all these tables, the most important is the Product table, all the other tables have info that will make richer the information of a product.

How it works
============

01. Run icecat-importer: java -jar icecat-importer.jar
02. Enter your Icecat credentials
03. Enter your Database credentials
04. Enter the number of products you want to import

Future goals
============
Depending on how popular this project becomes, I have the following goals for future versions:

- Add support for more databases.
- Add support for all the languages supported by Icecat.
- Store objects to the database meanwhile others are being parsed. (Create a queue of objects)

