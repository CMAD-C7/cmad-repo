import store from '../stores/store.js';

function fetchMessageCount() {
    return fetch('data/messagecount.json').then(function(response){
            return response.json();
        }).then(function(messagecount){
            store.dispatch({
                type: 'messagecount',
                messagecount: messagecount
            });
        });
};

export default fetchMessageCount;