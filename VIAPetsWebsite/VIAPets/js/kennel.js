
$(document).ready(function () {
    var maxLimit = 10;
    var kennelReservations = []; 

    
    $.ajax({
        type: "GET",
        url: "../../../reservationList.xml",
        dataType: "xml",
        success: function (xml) {
            $(xml).find('kennelReservations').each(function () {
                const startDate = new Date(
                    $(this).find('startDate').find('year').text(),
                    $(this).find('startDate').find('month').text() - 1,
                    $(this).find('startDate').find('day').text()
                );
                const endDate = new Date(
                    $(this).find('endDate').find('year').text(),
                    $(this).find('endDate').find('month').text() - 1,
                    $(this).find('endDate').find('day').text()
                );
                kennelReservations.push({ startDate, endDate });
            });
            
            maxLimit = parseInt($(xml).find('maxlimit').text())
        },
        error: function (xhr, status, error) {
            console.error('Error loading XML:', error);
        }
    });

    function howMuchIsAvailable(startDate, endDate) {
        let reservedCount = 0;

        kennelReservations.forEach((reservation) => {
            if (!(reservation.endDate < startDate || reservation.startDate >= endDate)) {
                reservedCount++;
                if (reservedCount = maxLimit) {
                    
                    return 0;
                }
            }
        });

        return Math.max(maxLimit - reservedCount, 0);
    }

    
    $('#selectDate').on('click', function () {
        const startDate = new Date($('#datePicker1').val());
        const endDate = new Date($('#datePicker2').val());

        
        if (isNaN(startDate) || isNaN(endDate)) {
            alert("Please select valid dates.");
            return;
        }
        if (startDate >= endDate) {
            alert("Start date must be before end date.");
            return;
        }

        
        const availableKennels = howMuchIsAvailable(startDate, endDate);

        
        if (availableKennels > 0) {
            $('#availableKennels').text(`${availableKennels}`);
        } else {
            $('#availableKennels').text("No kennels available for the selected dates.");
        }
    });
});
