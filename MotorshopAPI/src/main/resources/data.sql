/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hungh
 * Created: Jun 9, 2021
 */

/*
delete from depart;
delete from staff;
delete from customer;
delete from brand;
delete from motor;*/

/*
--depart
INSERT INTO depart VALUES
    ("BH", "Ban Hang"),
    ("BD", "Bao Duong"),
    ("GD", "Giam Doc");



--staff
INSERT INTO staff VALUES
    ("ST01", "Tu Anh", "0123456111", "b59c67bf196a4758191e42f76670ceba", "01/03/2021 07:00:00", "GD"), --pass plaintext: 1111
    ("ST02", "Le Ngoc Khanh", "0123456222", "934b535800b1cba8f96a5d72f72f1611", "02/03/2021 16:00:00", "BH"),
    ("ST03", "Thu Huong", "0123456333", "2be9bd7a3434f7038ca27d1918de58bd", "03/03/2021 06:00:00", "BH"), --pass plaintext: 3333
    ("ST04", "Kha Ngan", "0123456444", "dbc4d84bfcfe2284ba11beffb853a8c4", "04/03/2021 08:00:00", "BH"),
    ("ST05", "Vinh Nhat Kha", "0123456555", "6074c6aa3488f3c2dddff2a7ca821aab", "05/03/2021 10:00:00", "BD"), --pass plaintext: 5555
    ("ST06", "Tan Loc", "0123456666", "e9510081ac30ffa83f10b68cde1cac07", "06/03/2021 12:00:00", "BD"),
    ("ST07", "Pham Khiem", "0123456777", "d79c8788088c2193f0244d8f1f36d2db", "07/03/2021 14:00:00", "BD"); --pass plaintext: 7777



--customer
INSERT INTO customer VALUES
    ("CM01", "302456811", "Le Van Hau", "97 Man Thien, Ho Chi Minh", "0347895411", "b59c67bf196a4758191e42f76670ceba", "11/03/2021 11:00:00"), --pass plaintext: 1111
    ("CM02", "302456822", "Tran Anh", "65 CMT8, Ho Chi Minh", "0347895422", "934b535800b1cba8f96a5d72f72f1611", "12/03/2021 12:00:00"),
    ("CM03", "302456833", "Vo Chi Trung", "156 3/2, Ho Chi Minh", "0347895433", "2be9bd7a3434f7038ca27d1918de58bd", "13/03/2021 13:00:00"), --pass plaintext: 3333
    ("CM04", "302456844", "Le Anh Tuyet", "7 Ly Thai To, Ho Chi Minh", "0347895444", "dbc4d84bfcfe2284ba11beffb853a8c4", "14/03/2021 14:00:00"),
    ("CM05", "302456855", "Nguyen Thuy Tran", "1 Hoa Hong, Ho Chi Minh", "0347895455", "6074c6aa3488f3c2dddff2a7ca821aab", "15/03/2021 15:00:00"); --pass plaintext: 5555



--brand
INSERT INTO brand(brand_id, name, address, phone, email) VALUES
    ("BR01", "Honda", "Phuc Thang, Phuc Yen, Vinh Phuc, Viet Nam", "18008001", "cr@honda.com.vn"),
    ("BR02", "Yamaha", "Binh An, Trung Gia, Soc Son, Ha Noi", "18001588", "cskh@yamaha-motor.com.vn"),
    ("BR03", "Sym", "4 5C, KCN Nhon Trach 2, Dong Nai", "0912111918", "cskhmn@sym.com.vn"),
    ("BR04", "Ohlins", "Box 722, 194 27 Upplands Vasby, Sweden", "1245777432", "service@ohlins.se"),
    ("BR05", "Akrapovic", "1295 Ivancna Gorica, Slovenia", "3456778998", "service@akrapovic.si");



--motor
INSERT INTO motor(name, amount, price, warranty_period, brand_id) VALUES
    ("Wave Alpha", "8", "18099000", "24", "BR01"),
    ("Winner X", "9", "45999000", "48", "BR01"),
    ("Vision", "10", "29999000", "36", "BR01"),
    ("Sirius", "8", "21099000", "36", "BR02"),
    ("Exciter", "9", "50499000", "48", "BR02"),
    ("Grande", "10", "49999000", "48", "BR02");



--motor_info
INSERT INTO motor_info(name) VALUES
    ("Số khung"),
    ("Số sườn"),
    ("Khối lượng"),
    ("Dài x Rộng x Cao"),
    ("Độ cao yên"),
    ("Khoảng sáng gầm xe"),
    ("Dung tích bình xăng"),
    ("Hộp số"),
    ("Loại động cơ"),
    ("Công suất tối đa"),
    ("Dung tích nhớt máy"),
    ("Dung tích xy-lanh");



--motor_detail
INSERT INTO motor_detail(motor_id, motor_info_id, content) VALUES
    ("1", "1", "1119991"),
    ("1", "2", "1119992"),
    ("1", "3", "97kg"),
    ("1", "4", "1.914mm x 688mm x 1.075mm"),
    ("1", "7", "3,7 lít"),

    ("2", "1", "2229991"),
    ("2", "2", "2229992"),
    ("2", "3", "Phiên bản phanh thường: 123kg Phiên bản phanh ABS: 124kg"),
    ("2", "4", "2.019 x 727 x 1.088 mm"),
    ("2", "7", "4,5 lít"),

    ("3", "1", "3339991"),
    ("3", "2", "3339992"),
    ("3", "3", "Phiên bản Tiêu chuẩn: 96kg Phiên bản Đặc biệt và Cao cấp: 97kg"),
    ("3", "4", "1.871mm x 686mm x 1.101mm"),
    ("3", "7", "4,9 lít"),

    ("4", "1", "4449991"),
    ("4", "2", "4449992"),
    ("4", "3", "96kg"),
    ("4", "4", "1.890mm x 665mm x 1.035mm"),
    ("4", "7", "4,2 lít"),

    ("5", "1", "5559991"),
    ("5", "2", "5559992"),
    ("5", "3", "121kg"),
    ("5", "4", "1,975 mm × 665 mm × 1,085 mm"),
    ("5", "7", "5,4 lít"),

    ("6", "1", "6669991"),
    ("6", "2", "6669992"),
    ("6", "3", "101 kg"),
    ("6", "4", "1.820mm x 685mm x 1.150mm"),
    ("6", "7", "4,4 lít");



--accessory
INSERT INTO accessory(name, amount, price, warranty_period, brand_id) VALUES
    ("Nhan dan trang tri Ohlins 1", "5", "403000", "3", "BR04"),
    ("Phuoc Ohlins Vario", "5", "8500000", "24", "BR04"),
    ("Nhan dan Akrapovic chong nhiet nhom", "5", "212000", "12", "BR04"),
    ("Po Akrapovic GP Titan Yamaha R3", "5", "8000000", "24", "BR04");



--accessory_detail
INSERT INTO accessory_detail(accessory_id, motor_id, price) VALUES
    ("1", "1", "350000"),
    ("1", "3", "450000"),
    ("1", "5", "400000"),

    ("2", "4", "8000000"),
    ("2", "6", "8500000"),
    ("2", "2", "9000000"),

    ("3", "1", "150000"),
    ("3", "2", "250000"),
    ("3", "3", "200000"),

    ("4", "4", "7500000"),
    ("4", "5", "8000000"),
    ("4", "6", "8500000");



--bill
INSERT INTO bill(created_date, customer_id, staff_id) VALUES
    ("01/04/2021 09:00:00", "CM01", "ST02"),
    ("01/04/2021 15:30:00", "CM02", "ST03"),
    ("25/04/2021 10:05:00", "CM01", "ST02"),
    ("02/05/2021 08:15:00", "CM04", "ST04"),
    ("02/05/2021 16:33:00", "CM04", "ST04"),
    ("08/05/2021 13:20:00", "CM03", "ST03"),
    ("12/05/2021 07:10:00", "CM05", "ST04");



--bill_detail
INSERT INTO bill_detail(bill_id, motor_id, amount, price) VALUES
    ("1", "1", "1", "18099000"),
    ("1", "5", "3", "50499000");
INSERT INTO bill_detail(bill_id, accessory_id, amount, price) VALUES
    ("1", "1", "2", "400000");
INSERT INTO bill_detail(bill_id, motor_id, amount, price) VALUES
    ("2", "5", "2", "50499000"),
    ("3", "4", "1", "21000000");
INSERT INTO bill_detail(bill_id, accessory_id, amount, price) VALUES
    ("3", "3", "3", "212000");
INSERT INTO bill_detail(bill_id, motor_id, amount, price) VALUES
    ("4", "3", "1", "29999000"),
    ("5", "6", "1", "49999000");
INSERT INTO bill_detail(bill_id, accessory_id, amount, price) VALUES
    ("5", "4", "1", "8000000");
INSERT INTO bill_detail(bill_id, motor_id, amount, price) VALUES
    ("6", "2", "4", "45999000"),
    ("6", "4", "1", "21100000"),   
    ("7", "3", "1", "29999000");



--warranty
INSERT INTO warranty(created_date, bill_id, staff_id) VALUES
    ("01/05/2021 10:00:00", "1", "ST05"),
    ("05/05/2021 10:00:00", "1", "ST05"),
    ("15/05/2021 08:11:00", "5", "ST07"),
    ("20/05/2021 09:22:00", "4", "ST06"),
    ("27/05/2021 21:28:37", "1", "ST07");



--warranty_detail
INSERT INTO warranty_detail(warranty_id, motor_id, content, price) VALUES
    ("1", "1", "thay co bo", "300000");
INSERT INTO warranty_detail(warranty_id, accessory_id, content, price) VALUES
    ("1", "1", "thay tem moi", "100000");
INSERT INTO warranty_detail(warranty_id, motor_id, content, price) VALUES
    ("2", "5", "thay vo ruot", "0"),
    ("3", "6", "thay tay thang", "350000");
INSERT INTO warranty_detail(warranty_id, accessory_id, content, price) VALUES
    ("3", "4", "thay tem moi", "100000");
INSERT INTO warranty_detail(warranty_id, motor_id, content, price) VALUES
    ("4", "3", "thay nhot", "0"),
    ("4", "3", "thay bugi", "0"),
    ("5", "5", "thay nhot", "0");
*/