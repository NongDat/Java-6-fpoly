const app = angular.module("app", []);
        app.controller("ctrl", function ($scope, $http) {
           $scope.import = function(files) {
            var reader = new FileReader();// để đọc files excel
            // Khi mà gọi reader gọi read... chúng ta đưa file đầu tiên trong tập hợp file chọn được
            // thì khi đó Js hay file reader đọc dữ liệu từ file đó. Sau khi đọc thành công 
            //  thì sự kiện onloadend xảy ra
            reader.onloadend = async () => { // sau khi sự kiện xảy ra thì toàn bộ dữ liệu đọc đc đặt vào file  
                //=> reader.result
                var workbook = new ExcelJS.Workbook();// chua toàn bộ file excel
                await workbook.xlsx.load(reader.result);// đọc file excel vào trong workbook
                // sau khi đọc xong thì ta mới đi đọc sheet
                const worksheet = workbook.getWorksheet('Sheet1');
                worksheet.eachRow((row, index) => {
                    if(index > 1) {
                        let student = {
                            email: row.getCell(1).value,
                            fullname: row.getCell(2).value,
                            marks: +row.getCell(3).value,// Dấu + tức là số
                            gender: true && row.getCell(4).value,// ép kiểu sang boolean
                            country: row.getCell(5).value
                        }
                        var url = "http://localhost:8080/rest/students";
                        $http.post(url, student).then(resp => {
                            console.log("Success", resp.data);
                        }).catch(err => {
                            console.log("Error", err);
                        });
                    }
                })

            };
           }
        });