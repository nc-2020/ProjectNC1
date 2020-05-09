import {FormGroup} from '@angular/forms';

export class HashCheckValidator {

  static passwordConfirming(form: FormGroup): { [key: string]: boolean} {
    const control = form.controls.confirmPassword;
    const matchingControl = form.controls.confirmPassword;
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ hashCheck: true });
      return {hashCheck: true};
    }
    return null;
  }
}
