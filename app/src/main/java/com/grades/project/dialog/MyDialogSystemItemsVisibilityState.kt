package com.grades.project.dialog

sealed interface MyDialogSystemItemsVisibilityState {

    object AllVisible : MyDialogSystemItemsVisibilityState

    object AllHidden : MyDialogSystemItemsVisibilityState

    object StatusBarVisible : MyDialogSystemItemsVisibilityState

    object NavigationBarVisible : MyDialogSystemItemsVisibilityState
}