import React from "react";
import IntervalSelector from "./IntervalSelector.jsx";
import MessageCountTable from "./MessageCountTable.jsx";
import fetchMessageCount from "../rest/ajax.js";

class MessageApp extends React.Component {
    constructor(props) {
        super(props);
        this.defaultInterval = 5000;
        fetchMessageCount();
        this.updateInterval = (newInterval) => {
            // Clearing previous interval
            clearInterval(this.interval);
            if(newInterval > 0) this.interval = setInterval(() => fetchMessageCount(), newInterval);
        }
    }

    componentDidMount() {
        this.interval = setInterval(() => fetchMessageCount(), this.defaultInterval);
    }
    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        return (
            <div>
                <IntervalSelector updateInterval={this.updateInterval} defaultInterval={this.defaultInterval}></IntervalSelector>
                <br/>
                <MessageCountTable></MessageCountTable>
            </div>
        );
    }
}

export default MessageApp;