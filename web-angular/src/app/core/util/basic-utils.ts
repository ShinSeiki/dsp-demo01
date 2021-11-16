import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { InfoDialogComponent } from "src/app/pages/demo01/util/dialog/info-dialog.component";

export class BasicUtils {

  static doShowHttpResponseErrorDialog(httpErr, modalService: NgbModal, dialogTitle: String, 
    classType: String = 'danger'): NgbModalRef {
    let dialog: NgbModalRef;
    let message: String;

    if (httpErr["error"] && httpErr["error"]["message"]) {
    message = httpErr["error"].message;
    } else {
    message = `Error al procesar la transaccion: ${httpErr.message}.`
    }

    dialog = InfoDialogComponent.doShow(
        modalService, classType, dialogTitle, message
    );

    return dialog;
  }

  static doShowDialog(componentDialog: any, modalService: NgbModal): NgbModalRef {
    let modalRef: NgbModalRef;

    modalRef = modalService.open(componentDialog, {
      size: 'lg',
      keyboard: false
    });

    return modalRef;
  }

}
