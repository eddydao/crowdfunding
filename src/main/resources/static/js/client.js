var API_KEY = $('#api-key').val();
// Create a Stripe client.
var stripe = Stripe(API_KEY);

// Create an instance of Elements.
var elements = stripe.elements();

// Create an instance of the card Element.
var card = elements.create('card',{
    style: {
        base: {
            iconColor: '#c4f0ff',
            color: '#fff',
            fontWeight: '500',
            fontFamily: 'Roboto, Open Sans, Segoe UI, sans-serif',
            fontSize: '16px',
            fontSmoothing: 'antialiased',
            ':-webkit-autofill': {
                color: '#fce883',
            },
            '::placeholder': {
                color: '#87BBFD',
            },
        },
        invalid: {
            iconColor: '#FFC7EE',
            color: '#FFC7EE',
        },
    },
});

// Add an instance of the card Element into the `card-element` <div>.
card.mount('#card-element');

// Handle real-time validation errors from the card Element.
card.addEventListener('change', function (event) {
    var displayError = document.getElementById('card-errors');
    if (event.error) {
        displayError.textContent = event.error.message;
    } else {
        displayError.textContent = '';
    }
});

// Handle form submission.
var form = document.getElementById('payment-form');
form.addEventListener('submit', function (event) {
    event.preventDefault();
    // handle payment
    handlePayments();
});

//handle card submission
function handlePayments() {
    stripe.createToken(card).then(function (result) {
        if (result.error) {
            // Inform the user if there was an error.
            var errorElement = document.getElementById('card-errors');
            errorElement.textContent = result.error.message;
        } else {
            // Send the token to your server.
            var token = result.token.id;
            var email = $('#email').val();
            var optionId = $('#optionId').val();
            var projectId = $('#projectId').val();

            $.post(
                "/create-charge",
                {email: email, token: token, optionId: optionId, projectId: projectId},
                function (data) {
                    console.log(data.details);
                    window.location.replace("@{/project/}"+ projectId );
                }, 'json');
        }
    });
}