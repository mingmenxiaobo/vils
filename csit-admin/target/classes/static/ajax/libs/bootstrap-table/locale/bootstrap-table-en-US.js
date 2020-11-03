(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['en-US'] = {
        formatLoadingMessage: function () {
            return 'Loading……';
        },
        formatRecordsPerPage: function (pageNumber) {
            return pageNumber + ' rows/page';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return 'From ' + pageFrom + ' To ' + pageTo + ' Rows，Total  ' + totalRows + ' Rows.';
        },
        formatSearch: function () {
            return 'Search';
        },
        formatNoMatches: function () {
            return 'No Data';
        },
        formatPaginationSwitch: function () {
            return 'Hidden/Show';
        },
        formatRefresh: function () {
            return 'Refresh';
        },
        formatToggle: function () {
            return 'Toggle';
        },
        formatColumns: function () {
            return 'Columns';
        },
        formatExport: function () {
            return 'Export';
        },
        formatClearFilters: function () {
            return 'Clear';
        }
    };




   // $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['en-US']);

})(jQuery);
