$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "../../../list.xml", 
        dataType: "xml",
        success: function (xml) {
            $(xml).find('pets').each(function () {
                var name = $(this).find('name').text();
                var species = $(this).find('species').text();
                var color = $(this).find('color').text();
                var age = $(this).find('age').text();
                var gender = $(this).find('gender').text();
                var price = $(this).find('price').text();
                let strPrice = price.slice(0, -2) + ' dkk'; 
                var type = $(this).find('type').text();
                var inKennel = $(this).find('inKennel').text(); 
                var nameOfBreeder = $(this).find('nameOfBreeder').text();

                
                if(inKennel === 'false'){

                    
                    if(type === '1'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/dog/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#dog .card-body .row').append(petHtml);
                    } else if(type === '2'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/cat/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#cat .card-body .row').append(petHtml);
                    } else if(type === '3'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/bird/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#bird .card-body .row').append(petHtml);
                    } else if(type === '4'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/fish/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#fish .card-body .row').append(petHtml);
                    } else if(type === '5'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/rodent/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#rodent .card-body .row').append(petHtml);
                    } else if(type === '6'){
                        var petHtml = `
                        <div class="onepet">
                            <div class="col-4 imgwrapper">
                                <img class="img-fluid petimg" src="../images/pets/various/${name}.jpg" alt="${name}">
                                <p class="name">${name}</p>
                            </div>
                            <div class="text-wrapper">
                                <p class="recieverinf">species<br>color<br>age<br>gender<br>price</p>
                                <p class="reciever">${species}<br>${color}<br>${age}<br>${gender}<br>${strPrice}</p>
                            </div>
                        </div>
                    `;

                        $('#various .card-body .row').append(petHtml);
                    }
                }        
            });
        },
        error: function (xhr, status, error) {
            console.error('Error loading XML:', error);
        }
    });
});
