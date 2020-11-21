(function($) {
    'use strict';
    // following symptoms button selection
    $('.selection-btn').on('click', function(event) {
        $(this).toggleClass('active');
    })


})(jQuery);
$(document).ready(function() {
    $(".getStart").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").show();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();




    });
    $(".backGetstart").click(function() {
        $(".getstarted").show();
        $(".firstStep").hide();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();



    });
    $(".nextStepOne").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").show();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();




    });
    $(".backStepTwoButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").show();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();



    });
    $(".nextStepTwo").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").show();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();





    });
    $(".backStepThreeButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").show();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();



    });
    $(".nextStepThree").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").show();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();



    });
    $(".backStepFourButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").show();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();


    });
    $(".nextStepFour").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").show();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();


    });
    $(".backStepFiveButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").show();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();


    });
    $(".nextStepFive").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").show();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();

    });
    $(".backStepSixButton").click(function() {

        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").show();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();
    });
    $(".nextStepSix").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").show();
        $(".stepEightInner").hide();

    });
    $(".backStepSevenButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").show();
        $(".stepSevenInner").hide();
        $(".stepEightInner").hide();
    });
    $(".nextStepSeven").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").hide();
        $(".stepEightInner").show();

    });
    $(".backStepEightButton").click(function() {
        $(".getstarted").hide();
        $(".firstStep").show();
        $(".stepOneInner").hide();
        $(".stepTwoInner").hide();
        $(".stepThreeInner").hide();
        $(".stepFourInner").hide();
        $(".stepFiveInner").hide();
        $(".stepSixInner").hide();
        $(".stepSevenInner").show();
        $(".stepEightInner").hide();
    });

    $(".yesButton").click(function() {
        if ($(this).attr('step') == 'one') {
            $(".stepOneNextBackSec").show();
        } else if ($(this).attr('step') == 'two') {
            $(".stepTwoNextBackSec").show();
        } else if ($(this).attr('step') == 'three') {
            $(".stepThreeNextBackSec").show();
        } else if ($(this).attr('step') == 'four') {
            $(".stepFourNextBackSec").show();
            $(".noButtonFour").hide();
        } else if ($(this).attr('step') == 'five') {
            $(".stepFiveNextBackSec").show();
        }



    });
    $(".noButton").click(function() {
        if ($(this).attr('step') == 'one') {
            $(".stepOneNextBackSec").hide();
            $(".getstarted").hide();
            $(".firstStep").show();
            $(".stepOneInner").hide();
            $(".stepTwoInner").show();
            $(".stepThreeInner").hide();
            $(".stepFourInner").hide();
            $(".stepFiveInner").hide();
            $(".stepSixInner").hide();
            $(".stepSevenInner").hide();
            $(".stepEightInner").hide();
        } else if ($(this).attr('step') == 'two') {
            $(".stepTwoNextBackSec").hide();
            $(".getstarted").hide();
            $(".firstStep").show();
            $(".stepOneInner").hide();
            $(".stepTwoInner").hide();
            $(".stepThreeInner").show();
            $(".stepFourInner").hide();
            $(".stepFiveInner").hide();
            $(".stepSixInner").hide();
            $(".stepSevenInner").hide();
            $(".stepEightInner").hide();
        } else if ($(this).attr('step') == 'three') {
            $(".stepThreeNextBackSec").hide();
            $(".getstarted").hide();
            $(".firstStep").show();
            $(".stepOneInner").hide();
            $(".stepTwoInner").hide();
            $(".stepThreeInner").hide();
            $(".stepFourInner").show();
            $(".stepFiveInner").hide();
            $(".stepSixInner").hide();
            $(".stepSevenInner").hide();
            $(".stepEightInner").hide();
        } else if ($(this).attr('step') == 'four') {
            $(".stepFourNextBackSec").hide();
            $(".getstarted").hide();
            $(".firstStep").show();
            $(".stepOneInner").hide();
            $(".stepTwoInner").hide();
            $(".stepThreeInner").hide();
            $(".stepFourInner").hide();
            $(".stepFiveInner").show();
            $(".stepSixInner").hide();
            $(".stepSevenInner").hide();
            $(".stepEightInner").hide();
        } else if ($(this).attr('step') == 'five') {
            $(".stepFiveNextBackSec").hide();
            $(".getstarted").hide();
            $(".firstStep").show();
            $(".stepOneInner").hide();
            $(".stepTwoInner").hide();
            $(".stepThreeInner").hide();
            $(".stepFourInner").hide();
            $(".stepFiveInner").hide();
            $(".stepSixInner").show();
            $(".stepSevenInner").hide();
            $(".stepEightInner").hide();
        }



    });


});


function shortnessYes() {
    alert("test");
}