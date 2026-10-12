
package Utilities;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author chanh
 */
public class Menu {

    public static void printMenu(String str) {
        List<String> menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select:")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }

    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }
}
//- `now()`: Lấy ngày hiện tại.
//- `of(year, month, day)`: Tạo ngày cụ thể.
//- `getYear()`: Lấy năm.
//- `getMonth()`: Lấy tháng dạng `Month` (ví dụ `OCTOBER`).
//- `getMonthValue()`: Lấy tháng dạng số từ 1–12.
//- `getDayOfMonth()`: Lấy ngày trong tháng.
//- `getDayOfWeek()`: Lấy thứ trong tuần.
//- `getDayOfYear()`: Lấy ngày thứ bao nhiêu trong năm.
//- `lengthOfMonth()`: Lấy số ngày trong tháng.
//- `lengthOfYear()`: Lấy số ngày trong năm (365 hoặc 366).
//- `isLeapYear()`: Kiểm tra năm nhuận.
//- `isBefore(date)`: Kiểm tra ngày hiện tại của đối tượng có trước ngày truyền vào không.
//- `isAfter(date)`: Kiểm tra ngày hiện tại của đối tượng có sau ngày truyền vào không.
//- `isEqual(date)`: Kiểm tra hai ngày có bằng nhau không.
//- `equals(date)`: So sánh hai ngày có bằng nhau không.
//- `plusDays(n)`: Cộng thêm `n` ngày.
//- `minusDays(n)`: Trừ đi `n` ngày.
//- `plusWeeks(n)`: Cộng thêm `n` tuần.
//- `minusWeeks(n)`: Trừ đi `n` tuần.
//- `plusMonths(n)`: Cộng thêm `n` tháng.
//- `minusMonths(n)`: Trừ đi `n` tháng.
//- `plusYears(n)`: Cộng thêm `n` năm.
//- `minusYears(n)`: Trừ đi `n` năm.
//- `withYear(year)`: Thay đổi năm.
//- `withMonth(month)`: Thay đổi tháng.
//- `withDayOfMonth(day)`: Thay đổi ngày trong tháng.
//- `withDayOfYear(day)`: Thay đổi ngày thứ bao nhiêu trong năm.
//- `parse(string)`: Chuyển chuỗi thành `LocalDate` (mặc định định dạng `yyyy-MM-dd`).
//- `toString()`: Chuyển ngày thành chuỗi theo định dạng `yyyy-MM-dd`.
//- `format(formatter)`: Định dạng ngày thành chuỗi theo mẫu mong muốn.
//- `atStartOfDay()`: Chuyển thành `LocalDateTime` với giờ `00:00`.
//- `atTime(hour, minute)`: Kết hợp ngày với giờ và phút để tạo `LocalDateTime`.
//
//Tính khoảng cách giữa hai ngày (`import java.time.temporal.ChronoUnit;`):
//
//- `ChronoUnit.DAYS.between(start, end)`: Tính số ngày giữa hai ngày.
//- `ChronoUnit.WEEKS.between(start, end)`: Tính số tuần giữa hai ngày.
//- `ChronoUnit.MONTHS.between(start, end)`: Tính số tháng trọn vẹn giữa hai ngày.
//- `ChronoUnit.YEARS.between(start, end)`: Tính số năm trọn vẹn giữa hai ngày.